package bg.sofia.uni.fmi.mjt.server;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import bg.sofia.uni.fmi.mjt.commands.FoodCommandsExecutor;
import bg.sofia.uni.fmi.mjt.commands.daily.intake.commands.DailyIntakeCommandsExecutor;
import bg.sofia.uni.fmi.mjt.exceptions.*;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static bg.sofia.uni.fmi.mjt.constants.ServerConstants.*;

public class FoodAnalyzerServer implements Closeable {

    private static Logger logger = Logger.getLogger(FoodAnalyzerServer.class);

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private ByteBuffer buffer;
    private Map<SocketChannel, String> clientsSockets;
    private FoodCommandsExecutor foodCommandsExecutor;
    private DailyIntakeCommandsExecutor dailyIntakeCommandsExecutor;

    private FoodAnalyzerServer(int port) throws IOException {
        openServerSocketChannel();
        openSelector();
        configureServerSocketChannel(port);
        initialiseResources();
        BasicConfigurator.configure();
    }

    private void openServerSocketChannel() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
    }

    private void openSelector() throws IOException {
        selector = Selector.open();
    }

    private void configureServerSocketChannel(int port) throws IOException{
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void initialiseResources() {
        buffer = ByteBuffer.allocate(CAPACITY);
        clientsSockets = new ConcurrentHashMap<>();
        foodCommandsExecutor = FoodCommandsExecutor.getInstance();
        dailyIntakeCommandsExecutor = DailyIntakeCommandsExecutor.getInstance();
    }

    private void start() throws IOException {
        int channels;
        SelectionKey selectionKey;
        Iterator<SelectionKey> selectionKeyIterator;
        while(serverSocketChannel.isOpen()) {
            channels = selector.select();
            if (channels == 0) {
                continue;
            }
            selectionKeyIterator = selector.selectedKeys().iterator();
            while (selectionKeyIterator.hasNext()) {
                selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) {
                    accept(selectionKey);
                }
                if (selectionKey.isReadable()) {
                    read(selectionKey);
                }
                selectionKeyIterator.remove();
            }
        }
    }

    private void accept(SelectionKey selectionKey) {
        try {
            SocketChannel socketChannel = ((ServerSocketChannel)selectionKey.channel()).accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            logger.error(CHANNEL_ACCEPT_MESSAGE, e);
        }
    }

    private void read(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        try {
            String inputData = null;
            buffer.clear();
            int numBytesRead = socketChannel.read(buffer);
            if (numBytesRead > 0) {
                buffer.flip();
                inputData = (new String(buffer.array(), StandardCharsets.US_ASCII)).substring(0, buffer.limit());
            }
            String response;
            if (inputData == null) {
                response = NO_COMMAND_MESSAGE;
            } else {
                response = processInputData(inputData, socketChannel);
            }
            sendResponse(socketChannel, response);
            closeChannelOnCondition(socketChannel, response);
        } catch (IOException e) {
            logger.error(String.format(SOCKET_CHANNEL_PROBLEM, e.getMessage()), e);
        }
    }

    private void sendResponse(SocketChannel socketChannel, String response) throws IOException {
        buffer.clear();
        buffer.put(response.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
    }

    private void closeChannelOnCondition(SocketChannel socketChannel, String response) throws IOException {
        if (response.equals(CLIENT_DISCONNECTED)) {
            removeSocketChannel(socketChannel);
            socketChannel.close();
        } else if (response.equals(TRUE) || response.equals(FALSE)) {
            socketChannel.close();
        }
    }

    private void removeSocketChannel(SocketChannel socketChannel) {
        clientsSockets.remove(socketChannel);
    }

    private String processInputData(String inputData, SocketChannel socketChannel) {
        if (inputData.contains(DISCONNECT)) {
            return CLIENT_DISCONNECTED;
        }
        if (inputData.contains(CHECK_COMMAND)) {
            String username = inputData.substring(inputData.indexOf(CHECK_COMMAND) + CHECK_COMMAND_LEN);
            return (clientsSockets.containsValue(username)) ? TRUE : FALSE;
        }
        if (inputData.contains(CONNECTION_COMMAND)) {
            String username = getConnectionCommandUsername(inputData);
            clientsSockets.putIfAbsent(socketChannel, username);
        }
        return getResponse(inputData, socketChannel);
    }

    private String getResponse(String inputData, SocketChannel socketChannel) {
        String response;
        try {
            response = foodCommandsExecutor.executeCommand(inputData);
        } catch (FoodNotFoundException | BarcodeNotFoundException | IMGNotFoundException | NDBNONotFoundException |
                DailyIntakeFileNotFoundException e) {
            response = e.getMessage();
        }
        if (response == null) {
            try {
                response = dailyIntakeCommandsExecutor.executeCommand(inputData + " " +
                        clientsSockets.get(socketChannel));
            } catch (FoodNotFoundException | BarcodeNotFoundException | IMGNotFoundException | NDBNONotFoundException |
                    DailyIntakeFileNotFoundException e) {
                response = e.getMessage();
            }
        }
        return response;
    }

    private String getConnectionCommandUsername(String inputData) {
        String[] parameters = inputData.split(SPACES_REGEX);
        StringBuilder username = new StringBuilder();
        for (int i = USER_NAME_START_INDEX + 1; i < parameters.length - 1; i++) {
            username.append(parameters[i]).append(" ");
        }
        username.append(parameters[parameters.length - 1]);
        return username.toString();
    }

    @Override
    public void close() {
        try {
            serverSocketChannel.close();
            selector.close();
        } catch (IOException e) {
            logger.fatal(CLOSING_RESOURCES_PROBLEM, e);
        }
    }

    public static void main(String[] args) {
        try (FoodAnalyzerServer foodAnalyzerServer = new FoodAnalyzerServer(PORT)){
            foodAnalyzerServer.start();
        } catch (IOException e) {
            logger.fatal(String.format(SERVER_PROBLEM, PORT), e);
        }
    }
}

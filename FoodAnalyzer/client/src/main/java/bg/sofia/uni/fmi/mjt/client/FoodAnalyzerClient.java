package bg.sofia.uni.fmi.mjt.client;

import bg.sofia.uni.fmi.mjt.validation.Validator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static bg.sofia.uni.fmi.mjt.constants.ClientConstants.*;

public class FoodAnalyzerClient {

    private static Logger logger = Logger.getLogger(FoodAnalyzerClient.class);

    private int remotePort;
    private String remoteHost;
    private ByteBuffer buffer;
    private String connectCommand;
    private Scanner inputScanner;
    private Validator validator;

    private FoodAnalyzerClient() {
        initialiseResources();
    }

    private void initialiseResources() {
        buffer = ByteBuffer.allocate(CAPACITY);
        inputScanner = new Scanner(System.in);
        validator = Validator.getInstance();
        BasicConfigurator.configure();
    }

    private void start() {
        getWelcomeMessage();
        processConnectCommand();
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(remoteHost, remotePort))) {
            String consoleInput;

            ClientThread client = new ClientThread(socketChannel);
            Thread clientThread = new Thread(client);
            clientThread.start();

            addToChannel(socketChannel, connectCommand);

            while (clientThread.isAlive()) {
                consoleInput = inputScanner.nextLine();
                if (validator.isValidCommand(consoleInput)) {
                    addToChannel(socketChannel, consoleInput);
                    if (consoleInput.equals(DISCONNECT)) {
                        Thread.sleep(CLIENT_THREAD_WAIT_TIME);
                    }
                } else  {
                    System.out.println(WRONG_COMMAND);
                }
            }
        } catch (IOException e) {
            inputScanner.close();
            logger.fatal(String.format(SOCKET_CHANNEL_PROBLEM, remotePort, remoteHost), e);
        } catch (InterruptedException e) {
            logger.error(THREAD_INTERRUPTED, e);
        }
        inputScanner.close();
    }

    private void getWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE + System.lineSeparator() + COMMANDS_LIST + System.lineSeparator() +
                IMPORTANT_MESSAGE);
    }

    private void processConnectCommand() {
        String command;
        while (true) {
            command = inputScanner.nextLine();
            if (validator.isConnectCommand(command)) {
                if (isUsernameAlreadyConnected(command)) {
                    System.out.println(String.format(CLIENT_ALREADY_CONNECTED, getUsername(command)));
                } else {
                    break;
                }
            } else {
                System.out.println(WRONG_COMMAND);
            }
        }
        connectCommand = command;
        String[] commands = command.split(SPACE_REGEX);
        remoteHost = commands[1];
        remotePort = Integer.parseInt(commands[2]);
    }

    private boolean isUsernameAlreadyConnected(String command) {
        String[] commands = command.split(SPACE_REGEX);
        String host = commands[1];
        int port = Integer.parseInt(commands[2]);
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(host, port))) {
            buffer.clear();
            buffer.put((CHECK_COMMAND + getUsername(command)).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            socketChannel.read(buffer);
            buffer.flip();
            String response = new String(buffer.array(), StandardCharsets.US_ASCII).substring(0, buffer.limit());

            return response.equals(TRUE);
        } catch (IOException e) {
            inputScanner.close();
            logger.fatal(String.format(SOCKET_CHANNEL_PROBLEM, port, host), e);
        }
        return false;
    }

    private String getUsername(String command) {
        String[] parameters = command.split(SPACES_REGEX);
        StringBuilder username = new StringBuilder();
        for (int i = USER_NAME_START_INDEX; i < parameters.length - 1; i++) {
            username.append(parameters[i]).append(" ");
        }
        username.append(parameters[parameters.length - 1]);
        return username.toString();
    }

    private void addToChannel(SocketChannel socketChannel, String command) {
        buffer.clear();
        buffer.put(command.getBytes());
        buffer.flip();
        try {
            socketChannel.write(buffer);
        } catch (IOException e) {
            logger.error(String.format(SOCKET_CHANNEL_WRITING_PROBLEM, command), e);
        }
    }

    public static void main(String[] args) {
        FoodAnalyzerClient foodAnalyzerClient = new FoodAnalyzerClient();
        foodAnalyzerClient.start();
    }
}

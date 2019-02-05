package bg.sofia.uni.fmi.mjt.client;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static bg.sofia.uni.fmi.mjt.constants.ClientConstants.*;

public class ClientThread implements Runnable {

    private static Logger logger = Logger.getLogger(ClientThread.class);

    private SocketChannel socketChannel;
    private ByteBuffer buffer;

    ClientThread(SocketChannel socketChannel) {
        initialiseResources(socketChannel);
        BasicConfigurator.configure();
    }

    private void initialiseResources(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.buffer = ByteBuffer.allocate(CAPACITY);
    }

    public void run() {
        String serverResponse;
        while (true) {
            try {
                buffer.clear();
                socketChannel.read(buffer);
                buffer.flip();
                serverResponse = new String(buffer.array(), StandardCharsets.US_ASCII).substring(0, buffer.limit());
                System.out.println(serverResponse);
                if (serverResponse.equals(CLIENT_DISCONNECTED)) {
                    break;
                }
            }
            catch (IOException e) {
                logger.error(String.format(RESPONSE_PROBLEM, socketChannel), e);
            }
        }
    }
}

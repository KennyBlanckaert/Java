package nioencryptionserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

public class Main {

    // Fields
    private static final int PORT = 7777;
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        
        EncryptionServer encryptionServer = new EncryptionServer(serverSocketChannel);
        Thread thread = new Thread(encryptionServer);
        thread.start();
        
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("exit")) {
            input = scanner.nextLine();
        }
    }
    
}

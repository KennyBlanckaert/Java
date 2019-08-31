package encryptionserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 9000;
    
    public static void main(String[] args) throws Exception {     
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running...");
              
        boolean running = true;
        while (running) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected! \n");
            
            Client client = new Client(socket);
            client.start();
        }      
    } 
}

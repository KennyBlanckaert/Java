package chatserver;

import entities.User;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionListener extends Thread {
    
    // Fields
    private static final int PORT = 9000;
            
    private ServerSocket serverSocket;
    private ChatRoom room;
    
    // Constructor
    public ConnectionListener() {    
        try {
            this.room = new ChatRoom();
            this.serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running...");
        }
        catch (Exception ex) {
            Logger.getLogger(ConnectionListener.class.getName()).log(Level.SEVERE, "ServerSocket stopped!");
        }
    }
    
    // Task
    @Override
    public void run() { 
        new Thread(this.room).start();
        
        boolean running = true;
        while(running) {
            try {
                Socket clientSocket = this.serverSocket.accept();
                
                User user = new User(clientSocket); 
                this.room.add(user);
            }
            catch (Exception ex) {
                Logger.getLogger(ConnectionListener.class.getName()).log(Level.SEVERE, "Failed to retrieve ClientSocket!");
            }
        }
    }
}

package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class User extends Thread {
    
    // Fields
    private String username;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    
    // Constructor
    public User(Socket socket) throws IOException {
        this.socket = socket;
        
        Random random = new Random();
        int id = random.nextInt(900) + 100;
        this.username = "user" + String.valueOf(id);
        
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.output = new PrintWriter(this.socket.getOutputStream(), true);
    }
    
    // Getters
    public String getUsername() {
        return this.username;
    }
    
    public void close() throws IOException {
        this.socket.close();
    }
    
    // Methods
    public void send(String message) {
        this.output.println(message);
    }
    
    public String receive() {
        try {
            if (this.input.ready()) {
                String message = this.input.readLine();
                return message;
            }
        }
        catch (Exception ex) {
            return "";
        }
        
        return "";
    }
}

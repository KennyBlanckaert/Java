package encryptionclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    // Fields
    private static final String HOST = "localhost";
    private static final int PORT = 9000;
    
    // Main
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        
        // Read command
        Scanner scanner = new Scanner(System.in);
        System.out.print("Terminal#> ");
        String command = scanner.nextLine();
        
        do {
           // Send message to the server
           writer.println(command);
           
           // Read response of the server
           String response = reader.readLine();
           
           // Show response
           System.out.println(response + "\n");
           
           // Read next command
           System.out.print("Terminal#> ");
           command = scanner.nextLine();
            
        } while (!command.equalsIgnoreCase("exit"));
    }
    
}

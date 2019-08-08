/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenny Blanckaert
 */
public class ConnectionCreator extends Thread {

    // Fields
    private static final Logger LOGGER = Logger.getLogger(ConnectionCreator.class.getName());
    private static final int PORT = 9001;
    private ServerSocket serverSocket;
    private BlockingQueue<Player> lobby;
    
    // Constructor
    public ConnectionCreator(BlockingQueue<Player> lobby) {
        this.lobby = lobby;
        try {
            this.serverSocket = new ServerSocket(PORT);
            LOGGER.log(Level.INFO, "Server is running...");
        } 
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failure while initializing ServerSocket");
        }
    }

    // Thread run 
    @Override
    public void run() {
        while (true) {
            try {
                Player player = new Player(serverSocket.accept());
                lobby.add(player);
            } catch (IOException ex) {
                LOGGER.log(Level.WARNING, "Failure while entering the lobby");
            }
        }
    }
}

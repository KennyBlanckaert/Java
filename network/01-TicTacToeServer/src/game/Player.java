/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenny Blanckaert
 */
public class Player extends Thread implements AutoCloseable {
    
    // Fields
    private final Socket socket;
    private Player opponent;
    private char symbol;
    
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());
    private BufferedReader commandReceiver;
    private PrintWriter clientChannel;
    
    // Constructor
    public Player(Socket socket) {
        this.socket = socket;
        try {
            this.commandReceiver = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.clientChannel = new PrintWriter(this.socket.getOutputStream(), true);
            clientChannel.println("WELCOME");
        } 
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failure while creating channel from server to client");
        }
    }
    
    // Functions
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
        clientChannel.println("MESSAGE opponent found...");
    }
    
    public void startGame(char symbol) {
        this.symbol = symbol;
        clientChannel.println("SYMBOL " + symbol);
        clientChannel.println("MESSAGE starting game...");
    }
    
    public int waitForPlayersMove() {
        int location = -1;
        try {
            String command = commandReceiver.readLine();
            if (command.startsWith("MOVE")) {
                location = Integer.parseInt(command.substring(5));
            }
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Failure while reading players move");
        }
        
        return location;
    }
    
    public void opponentMoved(int location) {
        clientChannel.println("OPPONENT_MOVED" + location);
    }
     
    public void sendMessage(String message) {
        clientChannel.println(message);
    }
    
    public boolean startNewGame() {
        
        boolean newGame = false;
        try {
            String command = commandReceiver.readLine();
            if (command.startsWith("MOVE")) {
                commandReceiver.readLine();
            }
            newGame = command.startsWith("REPLAY");
        } 
        catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newGame;
    }

    @Override
    public void close() {
        try {
            this.socket.close();
        }
        catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Failure while closing socket");
        }
    }
    
}

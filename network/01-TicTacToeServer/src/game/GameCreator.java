/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenny Blanckaert
 */
public class GameCreator extends Thread {

    // Fields
    private static final Logger LOGGER = Logger.getLogger(GameCreator.class.getName());
    private BlockingQueue<Player> lobby;
    private final ExecutorService executorService;
    
    // Constructor
    public GameCreator(BlockingQueue<Player> lobby) {
        this.lobby = lobby;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    // Thread run
    @Override
    public void run() {

        while (true) {
            try {
                Player playerOne = lobby.take();
                Player playerTwo = lobby.take();
                Game game = new Game(lobby, playerOne, playerTwo);
                this.executorService.execute(game);
            } 
            catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Failure while creating a game");
            }
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenny Blanckaert
 */
public class Game implements Runnable {

    // Fields
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private BlockingQueue<Player> lobby;
    private static final Character X = 'X';
    private static final Character O = 'O';
    private final Map<Character, Player> players;
    private Character currentPlayer;
    private final Character[] board = {
        null, null, null,
        null, null, null,
        null, null, null
    };
 
    // Constructor
    public Game(BlockingQueue<Player> lobby, Player playerOne, Player playerTwo) {
        this.lobby = lobby;
        this.players = new HashMap<>();
        this.players.put(X, playerOne);
        this.players.put(O, playerTwo);
        this.players.get(X).setOpponent(playerTwo);
        this.players.get(O).setOpponent(playerOne);
        this.players.get(X).startGame(X);
        this.players.get(O).startGame(O);
    }
    
    // Functions
    public boolean hasWinner() {
        return (this.board[0] != null && this.board[1] == this.board[0] && this.board[2] == this.board[1]
                || this.board[3] != null && this.board[4] == this.board[3] && this.board[5] == this.board[4]
                || this.board[6] != null && this.board[7] == this.board[6] && this.board[8] == this.board[7]
                || this.board[0] != null && this.board[3] == this.board[0] && this.board[6] == this.board[3]
                || this.board[1] != null && this.board[4] == this.board[1] && this.board[7] == this.board[4]
                || this.board[2] != null && this.board[5] == this.board[2] && this.board[8] == this.board[5]
                || this.board[0] != null && this.board[4] == this.board[0] && this.board[8] == this.board[4]
                || this.board[2] != null && this.board[4] == this.board[2] && this.board[6] == this.board[4]);
    }
    
    public boolean movesLeft() {
        for (Character character : board) {
            if (character == null) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean placeMoveIfValid(int location, Character character) {
        if (location >= 0 && location <= 8  && board[location] == null) {
            board[location] = character;
            return true;
        }
        
        return false;
    }
    
    // Thread run
    @Override
    public void run() {
        
        int location = -1;
        boolean hasWinner = false;
        boolean boardFilledUp = false;
        
        currentPlayer = X;
        Player player = players.get(currentPlayer);

        while (!boardFilledUp && !hasWinner) {
            
            // Ask the current player to move
            player.sendMessage("MESSAGE Your move");
            location = player.waitForPlayersMove();

            if (placeMoveIfValid(location, currentPlayer)) {
                
                // Inform current player
                player.sendMessage("VALID_MOVE");
                hasWinner = hasWinner();
                boardFilledUp = movesLeft();
                if (hasWinner) { player.sendMessage("VICTORY"); }
                if (boardFilledUp) { player.sendMessage("TIE"); }
                
                // Inform opponent
                currentPlayer = currentPlayer == X ? O : X;
                player = players.get(currentPlayer);
                player.opponentMoved(location);
                if (hasWinner) { player.sendMessage("DEFEAT"); }
                if (boardFilledUp) { player.sendMessage("TIE"); }
                
            } 
            else if (location == -1) {    
                
                // Connection to the player lost: stop game
                currentPlayer = currentPlayer == X ? O : X;
                player = players.get(currentPlayer);
                player.sendMessage("QUIT");
                if (player.startNewGame()) { lobby.offer(player); }
                player.close();
                break;
            } 
            else {
                
                // Unvalid move
                player.sendMessage("MESSAGE ?");
            }
        }
        
        // Replay?
        if (location != -1 && !movesLeft()) {
            boolean replayPlayerOne = players.get(X).startNewGame();
            boolean replayPlayerTwo = players.get(O).startNewGame();
            
            if (replayPlayerOne) { lobby.offer(players.get(X)); }
            if (replayPlayerTwo) { lobby.offer(players.get(O)); }
            
            if (replayPlayerOne && replayPlayerTwo) { /* Both players will replay */ }
            else if (!replayPlayerOne) { players.get(O).sendMessage("QUIT"); }
            else if (!replayPlayerTwo) { players.get(X).sendMessage("QUIT"); }
        }
    }
}

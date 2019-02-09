/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Kenny Blanckaert
 */
public class TicTacToeServer {
    
    public static void main(String[] args) {
        BlockingQueue<Player> lobby = new LinkedBlockingQueue<>();
        ConnectionCreator connectionCreator = new ConnectionCreator(lobby);
        GameCreator gameCreator = new GameCreator(lobby);
        
        new Thread(connectionCreator).start();
        new Thread(gameCreator).start();
    }
}

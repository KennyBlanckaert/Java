/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionserver;

import java.nio.channels.Selector;
import java.util.TimerTask;

/**
 *
 * @author Kenny Blanckaert
 */
public class WaitForOffer extends TimerTask {

    private final Selector selector;
    
    public WaitForOffer(Selector selector) {
        this.selector = selector;
    }
    
    @Override
    public void run() {
        // Finish TimerTask immediately
        selector.wakeup();
    }
    
}

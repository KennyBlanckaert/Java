/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Kenny Blanckaert
 */
public class AuctionListener implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(AuctionListener.class.getName());
    
    @Override
    public void onMessage(Message m) {
        try {
            String message = m.getBody(String.class);
            
            LOGGER.log(Level.INFO, "MESSAGE RECEIVED");
            
            if (message.contains("SOLD FOR")) {
                System.out.println(message);
            } 
        } 
        catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, "Failure in MessageListener");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionserver;

import auctionserver.entities.Auction;
import auctionserver.entities.Item;
import auctionserver.entities.Offer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.LinkedList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author Kenny Blanckaert
 */
public class AuctionThread implements Runnable {
    
    // Constants
    private static final Logger LOGGER = Logger.getLogger(AuctionThread.class.getName());
    private static final Long ONE_MINUTE = 60000l;
    private static final int BUFFER_SIZE = 255;

    // Mandatory to create object
    private final JMSContext context;
    private final Queue queue;
    private final ServerSocketChannel channel;
    
    // Used while running
    private boolean running;
    private final Auction auction;
    private final LinkedList<SocketChannel> clients;  
    private final CharsetDecoder decoder;
    private final Timer timer;
    
    // Used for managing multiple channels by one thread
    // Context-switching is expensive, so use as less threads as possible
    private Selector selector;
    
    // Reinitialized every offer
    private Offer offer;
    private WaitForOffer waitForOffer;
    
    // Constructor
    public AuctionThread(ConnectionFactory connectionFactory, Queue queue, ServerSocketChannel channel) {
        this.context = connectionFactory.createContext();
        this.queue = queue;
        this.channel = channel;
        
        this.auction = new Auction();
        this.running = true;
        this.clients = new LinkedList<>();
        this.decoder = Charset.forName("US-ASCII").newDecoder();
        this.timer = new Timer();
    }
    
    // Runnable functions
    @Override
    public void run() {
        try {
            this.selector = Selector.open();
            this.channel.configureBlocking(false);
            this.channel.register(this.selector, SelectionKey.OP_ACCEPT);
            
            LOGGER.log(Level.INFO, "Everything ready to start the auction");        
            startAuction();
            
        } 
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failure while opening selector");
        }
    }

    // Auction Process Functions
    private void startAuction() {
        LOGGER.log(Level.INFO, "Starting auction");
        
        newOffer();
        while (this.running) {
            idle();
        }
    }
    
    public void stopAuction() {
        LOGGER.log(Level.INFO, "Terminating auction");
        
        this.running = false;
        this.timer.cancel();
        this.waitForOffer.cancel();
        this.selector.wakeup();
    }

    private void newOffer() {
        
       // Get new item from the database and spread it
       LOGGER.log(Level.INFO, "NEW OFFER");
       Item item = this.auction.getItem();
       if (item != null) {
           this.offer = new Offer(item);
           deliverOfferToClients(this.offer);
           this.waitForOffer = new WaitForOffer(this.selector);
           this.timer.schedule(this.waitForOffer, ONE_MINUTE);
       }
       else {
           stopAuction();
       }
    }
    
    private void finishOffer() {
        LOGGER.log(Level.INFO, "OFFER FINISHED");
        
        String message = "ITEM " + this.offer.getItem().getDescription();
        if (this.offer.isSold()) {
            message += " SOLD FOR " + this.offer.getCurrentOffer();
            registerItemSold(this.offer);
        } 
        else {
            message += " NOT SOLD";
            registerItemNotSold(this.offer);
        }
        sendMessage(message);
    }
    
    private void idle() {
        
        // Go into idle state while waiting for offers, but keep checking possible incoming messages
        try {
            if (this.selector.select() > 0) {
                for (SelectionKey key : this.selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        addClient(key);
                    }
                    else if (key.isReadable()) {
                        readMessage(key);
                    }
                }
                
                // Clear !!! (Otherwise previous available channels are NOT deleted)
                this.selector.selectedKeys().clear();
            }
            else {
                finishOffer();
                newOffer();
            }     
        } 
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failure while asking for available messages");
        }
    }
    
    private void addClient(SelectionKey key) {
        try {
            ServerSocketChannel channelOfKey = (ServerSocketChannel) key.channel();
            SocketChannel clientChannel = channelOfKey.accept();
            sendMessage("WELCOME", clientChannel);   
            
            clientChannel.configureBlocking(false);
            clientChannel.register(this.selector, SelectionKey.OP_READ);
            
            LOGGER.log(Level.INFO, "NEW CLIENT CONNECTED");
            this.clients.add(clientChannel);
            
            String offerMessage = "ITEM " + this.offer.getItem().getId() + ": " + this.offer.getItem().getDescription() + "\nMINIMUM PRICE " + offer.getItem().getMinPrice();
            sendMessage(offerMessage, clientChannel);
        } 
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failure while adding client");
        }   
    }
    
    private void readMessage(SelectionKey key) {
        try {
            // Get channel & attachment
            SocketChannel channel = (SocketChannel) key.channel();

            // Read message
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            long length = channel.read(buffer);

            // Handle error message
            if (length == -1) {
                channel.close();
                this.clients.remove(channel);
                return;
            } 

            // Decode bytes to string
            buffer.flip();
            String command = this.decoder.decode(buffer).toString();

            /* Parse commands: REGISTER - OFFER - QUIT */
            LOGGER.log(Level.INFO, "PARSING COMMAND: {0}", command);
            if (command.startsWith("OFFER")) {
                LOGGER.log(Level.INFO, "OFFER RECEIVED");
                String argument = command.substring(6).trim(); 
                try {
                    double price = Double.parseDouble(argument);
                    if (this.offer.offerAccepted(price)) {
                        LOGGER.log(Level.INFO, "OFFER ACCEPTED");
                        
                        sendMessage("OFFER MADE: " + price);
                        
                        // Reschedule timer
                        this.waitForOffer.cancel();
                        this.waitForOffer = new WaitForOffer(this.selector);
                        this.timer.schedule(this.waitForOffer, ONE_MINUTE);
                    } 
                    else {
                        sendMessage("OFFER DENIED", channel);
                    }
                } 
                catch (NumberFormatException ex) {
                    sendMessage("OFFER NOT VALID", channel);
                }
            } 
            else if (command.startsWith("QUIT")) {
                LOGGER.log(Level.INFO, "CLIENT LEAVES");
                
                // If client quits with CTRL+C, the server CANNOT detect this!
                // isConnected() function does only check availability from serverside
                channel.close();
                clients.remove(channel);
            } 
            else {
                sendMessage("WRONG COMMAND", channel);
            }
        } 
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failure while reading message");
        } 
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failure while reading message");
        }
    }

    private void deliverOfferToClients(Offer offer) {
        String offerMessage = "ITEM " + offer.getItem().getId() + ": " + offer.getItem().getDescription() + "\nMINIMUM PRICE " + offer.getItem().getMinPrice();
        sendMessage(offerMessage);
    }

    private void sendMessage(String message) {
        LOGGER.log(Level.INFO, "SENDING MESSAGE TO ALL CLIENTS");
        
        // Send in blocks of 255 bits to clients
        ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.putChar('\n');
        buffer.putChar('\n');
        buffer.flip();
        
        this.clients.forEach((socketChannel) -> {
            int sent = 0;
            int total = buffer.remaining();
            
            while (sent != total) {
                try {
                    sent += socketChannel.write(buffer);
                } 
                catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Failure while sending offer");
                }
            }
        });  
    }
    
    private void sendMessage(String message, SocketChannel socketChannel) {
        LOGGER.log(Level.INFO, "SENDING MESSAGE TO ONE CLIENT");
        
        // Send in blocks of 255 bits to clients
        ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.putChar('\n');
        buffer.putChar('\n');
        buffer.flip();
        
        int sent = 0;
        int total = buffer.remaining();

        while (sent != total) {
            try {
                sent += socketChannel.write(buffer);
            } 
            catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Failure while sending offer");
            }
        } 
    }

    private void registerItemSold(Offer offer) {
        try {
            // Detected by SoldConsumer
            Message message = context.createTextMessage("ITEM " + offer.getItem().getId() + " NOT SOLD. "+ " LAST OFFER WAS " + offer.getCurrentOffer() + "\n\n");
            message.setBooleanProperty("SOLD", false);
            
            // this.context.createProducer().send(topic, "MyTopic");
            this.context.createProducer().send(queue, message);
        } 
        catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, "Failure while registering sold item");
        }
    }

    private void registerItemNotSold(Offer offer) {
        try {
            // Detected by NotSoldConsumer
            Message message = context.createTextMessage("ITEM " + offer.getItem().getId() + " SOLD FOR " + offer.getCurrentOffer() + "\n\n");
            message.setBooleanProperty("SOLD", true);
            
            // this.context.createProducer().send(topic, "MyTopic");
            this.context.createProducer().send(queue, message);
        } 
        catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, "Failure while registering unsold item");
        }
    }
}

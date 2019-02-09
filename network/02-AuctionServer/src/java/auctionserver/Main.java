/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * Execute commands in asadmin to create jms.ConnectionFactory & jms.Queue
 * Use 'ncat localhost 7777' command to create a client
 * Enter configured commands in the interactive session
 * @author Kenny Blanckaert
 */
public class Main {
    
    private static final int PORT = 7777;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    /* asadmin create-jms-resource 
     *      --restype javax.jms.Queue 
     *      --property Name=PhysicalQueue 
     *      jms/MyQueue
     */
    @Resource(lookup = "jms/MyQueue") 
    private static Queue queue; 
    
    // ================ TOPIC INSTEAD OF QUEUE ==================
    /* asadmin create-jms-resource 
     *      --restype javax.jms.Topic 
     *      --property Name=AuctionTopic
     *      jms/MyTopic
     */
    //@Resource(lookup = "jms/MyTopic")
    //private static Topic topic;
    
    /* asadmin create-jdbc-connection-pool 
     *      --datasourceclassname org.apache.derby.jdbc.ClientDataSource 
     *      --restype javax.sql.DataSource 
     *      --property portNumber=1527:password=Azerty123:user=Kenny:serverName=localhost:databaseName=auction 
     *      AuctionConnectionPool
     *
     * asadmin create-jdbc-resource 
     *      --connectionpoolid 
     *      AuctionConnectionPool jdbc/auction
     */
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory") 
    private static ConnectionFactory connectionFactory;

    public static void main(String[] args) {
        try {
            // Make server available on port 7777
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.socket().bind(new InetSocketAddress(PORT));
            
            // Start a new thread for the auction
            AuctionThread auctionThread = new AuctionThread(connectionFactory, queue, channel);
            Thread thread = new Thread(auctionThread);
            thread.start();
            
            // User can exit by entering 'quit'
            Scanner scanner = new Scanner(System.in);
            String input = "start";
            while (!input.equalsIgnoreCase("quit")) {
                input = scanner.nextLine();
            }
            
            // Stop the auction
            auctionThread.stopAuction();
            
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failure while opening ServerSocketChannel");
        }
    }
    
}

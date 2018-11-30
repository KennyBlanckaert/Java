/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 *
 * @author Kenny Blanckaert
 */
public class SoldConsumer {

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
     */
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory") 
    private static ConnectionFactory connectionFactory;

    
    public static void main(String[] args) {
        try (JMSContext context = connectionFactory.createContext();) {
            
            // Create a JMSConsumer listening to jms/MyQueue messages with property SOLD = true
            //JMSConsumer consumer = context.createDurableConsumer(topic, "MyTopic");
            JMSConsumer consumer = context.createConsumer(queue, "SOLD = true");

            // Consume messages from queue
            AuctionListener listener = new AuctionListener();
            consumer.setMessageListener(listener);

            // User can exit by entering 'quit'
            Scanner scanner = new Scanner(System.in);
            String input = "start";
            while (!input.equalsIgnoreCase("quit")) {
                input = scanner.nextLine();
            }
        }
    }

}

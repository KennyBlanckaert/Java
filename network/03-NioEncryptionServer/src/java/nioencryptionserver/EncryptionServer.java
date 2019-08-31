/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nioencryptionserver;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Hoofdgebruiker
 */
public class EncryptionServer implements Runnable {
    
    // Fields
    private boolean running = false;
    private static int number_of_requests;
    
    private ServerSocketChannel serverChannel;
    private JMSContext context;
    private Selector selector;
    private CharsetDecoder decoder;
    private LinkedList<SocketChannel> clients;
    private SecretKey key;

    
    // Constructor
    public EncryptionServer(ServerSocketChannel channel) throws NoSuchAlgorithmException {
        this.serverChannel = channel;
        this.decoder = Charset.forName("US-ASCII").newDecoder();
        this.clients = new LinkedList<>();
        this.key = KeyGenerator.getInstance("DES").generateKey();
        
        this.running = true;
        this.number_of_requests = 0;
        System.out.println("Server is running...");
    }

    // Task
    @Override
    public void run() {
        try {
            this.selector = Selector.open();
            this.serverChannel.configureBlocking(false);
            this.serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (this.running) {
                answerRequests();
            }
            
            this.selector.close();
        } 
        catch (Exception ex) {

        } 
    }
    
    private void answerRequests() throws Exception {
        int readyChannels = this.selector.select();
        if (readyChannels > 0) {
            for(SelectionKey selectionKey : this.selector.selectedKeys()) {
                if (selectionKey.isReadable()) {
                    handleRequest(selectionKey);
                }
                else if (selectionKey.isAcceptable()) {
                    addNewClient(selectionKey);
                }
            }
        }
    }
    
    private void addNewClient(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        
        System.out.println("Client connected!");
        
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        
        this.clients.add(channel);
    }
    
    private void handleRequest(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        
        ByteBuffer buffer = ByteBuffer.allocate(255);
        long size = channel.read(buffer);
        
        if (size < -1) {
            channel.close();
            this.clients.remove(channel);
            System.out.println("Uhh, what is this? Client is mocking me... BYE!");
        }
        else {
            buffer.flip();
            String message = this.decoder.decode(buffer).toString();
            
            this.number_of_requests++;
            System.out.println("Received a request (" + this.number_of_requests + ")");
            System.out.println(message);
            System.out.println("");

            if (message.toUpperCase().startsWith("ENCRYPT") || message.toUpperCase().startsWith("DECRYPT")) {
                String response = "ERROR";
                String text = message.substring(8);
                switch(message.toUpperCase().substring(0, 7)) {
                    case "ENCRYPT":
                        response = this.encrypt(text);
                        break;
                    case "DECRYPT":
                        response = this.decrypt(text);
                        break;
                    default:
                        break;
                }

                System.out.println("Sending back: " + response);
                sendMessage(response, channel);
            }
            else {
                sendMessage("Buhh, do not treat me like Artificial Intelligence!", channel);
            }

            System.out.println("Request completed :) \n");
        }
    }
    
    private void sendMessage(String message, SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(255);
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.putChar('\n');
        buffer.flip();
        while(buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }

    private String encrypt(String text) throws Exception {
             
        // Creating a Cipher object
        Cipher cipher = Cipher.getInstance("DES");

        // Initializing a Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, this.key);

        // Encrypting the data
        byte[] input = text.getBytes();	  	 
        byte[] enc = cipher.doFinal(input);
        String encoded = new String(BASE64EncoderStream.encode(enc));
        
        return encoded;
    }
    
    private String decrypt(String ciphertext) throws Exception {     

        // Creating a Cipher object
        Cipher cipher = Cipher.getInstance("DES");

        // Initializing a Cipher object
        cipher.init(Cipher.DECRYPT_MODE, this.key);

        // Decrypting the data
        byte[] input = ciphertext.getBytes();
        byte[] dec = BASE64DecoderStream.decode(input);
        String decoded = new String(cipher.doFinal(dec), "UTF-8");
 
        return decoded;
    }
    
}

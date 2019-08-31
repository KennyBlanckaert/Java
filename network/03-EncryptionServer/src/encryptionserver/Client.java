/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptionserver;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Hoofdgebruiker
 */
public class Client extends Thread {
    
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    private SecretKey key;
    
    private boolean running;
    private int number_of_requests;
    
    public Client(Socket socket) throws Exception {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        
        this.running = true;
        this.number_of_requests = 0;
        this.key = KeyGenerator.getInstance("DES").generateKey();
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
    
    @Override
    public void run() {
        try {
            while (this.running) {
                String request = this.reader.readLine();
                
                this.number_of_requests++;
                System.out.println("Received a request (" + this.number_of_requests + ")");
                System.out.println(request);
                System.out.println("");
                
                if (request.toUpperCase().startsWith("ENCRYPT") || request.toUpperCase().startsWith("DECRYPT")) {
                    String response = "ERROR";
                    String text = request.substring(8);
                    switch(request.toUpperCase().substring(0, 7)) {
                        case "ENCRYPT":
                            response = this.encrypt(text);
                            break;
                        case "DECRYPT":
                            response = this.decrypt(text);
                            break;
                        default:
                            break;
                    }

                    writer.println(response);
                }
                else {
                    writer.println("Buhh, do not treat me like Artificial Intelligence!");
                }
                
                System.out.println("Request completed :) \n");
            }
        }
        catch (Exception ex) {
            
        }
    }
}

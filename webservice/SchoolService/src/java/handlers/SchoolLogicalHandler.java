/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author Kenny Blanckaert
 */
public class SchoolLogicalHandler implements LogicalHandler<LogicalMessageContext> {
    
    @Override
    public boolean handleMessage(LogicalMessageContext messageContext) {
        boolean outgoing = (boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        // This can be used to inspect the message
        if (outgoing) {
            try {
                LogicalMessage msg = messageContext.getMessage();
                Source payload = msg.getPayload();

                // Inspect the message
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                StreamResult result = new StreamResult(outStream);
                transformer.transform(payload, result);
                
                String message = outStream.toString();
                
                // Write to logs
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
                Date date = new Date();
                
                File file = new File("C:/logs/service_call_results.log");
                file.getParentFile().mkdirs();
                if (!file.exists()) {
                    file.createNewFile();
                }
                
                int openedTag; 
                int closedTag;
                
                openedTag = message.indexOf("<Teacher>");
                closedTag = message.indexOf("</Teacher>");       
                if(openedTag != -1 && closedTag != -1) {
                    Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));                   
                    fileWriter.write(dateFormat.format(date).toString() + " --- getTeachers() called \r\n");
                    fileWriter.close();
                    Logger.getLogger(SchoolLogicalHandler.class.getName()).log(Level.INFO, "getTeachers() called");
                }
                
                openedTag = message.indexOf("<Student>");
                closedTag = message.indexOf("</Student>");       
                if(openedTag != -1 && closedTag != -1) {
                    Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));                   
                    fileWriter.write(dateFormat.format(date).toString() + " --- getStudents() called \r\n");
                    fileWriter.close();
                    Logger.getLogger(SchoolLogicalHandler.class.getName()).log(Level.INFO, "getStudents() called");
                }
                
                openedTag = message.indexOf("<Course>");
                closedTag = message.indexOf("</Course>");       
                if(openedTag != -1 && closedTag != -1) {
                    Writer fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));                   
                    fileWriter.write(dateFormat.format(date).toString() + " --- getCourses() called \r\n");
                    fileWriter.close();
                    Logger.getLogger(SchoolLogicalHandler.class.getName()).log(Level.INFO, "getCourses() called");
                }
                
            } 
            catch (TransformerConfigurationException ex) {
                Logger.getLogger(SchoolLogicalHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (TransformerException ex) {
                Logger.getLogger(SchoolLogicalHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(SchoolLogicalHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SchoolLogicalHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex) {
                Logger.getLogger(SchoolLogicalHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    @Override
    public boolean handleFault(LogicalMessageContext messageContext) {
        return true;
    }
    
    @Override
    public void close(MessageContext context) {
    }
    
}

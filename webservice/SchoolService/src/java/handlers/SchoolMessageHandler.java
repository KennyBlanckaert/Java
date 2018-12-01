package handlers;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Document;

/**
 *
 * @author Kenny Blanckaert
 */
public class SchoolMessageHandler implements SOAPHandler<SOAPMessageContext> {
    
    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        boolean outgoing = (boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        // This can be used to change the message (e.g. offer multiple languages)
        if (outgoing) {
            try {
                SOAPMessage msg = messageContext.getMessage();         
                Source source = msg.getSOAPPart().getContent();

                // Build new message
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer tf = factory.newTransformer(new StreamSource(this.getClass().getClassLoader().getResourceAsStream("handlers/translation.xsl")));

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = docFactory.newDocumentBuilder();

                Document document = builder.newDocument();
                DOMResult result = new DOMResult(document);
                tf.transform(source, result);
                
                StreamResult stringResult = new StreamResult(System.out);
                tf.transform(source, stringResult);

                // Change the message
                msg.getSOAPPart().setContent(new DOMSource(document));
            } 
            catch (SOAPException ex) {
                Logger.getLogger(SchoolMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (TransformerConfigurationException ex) {
                Logger.getLogger(SchoolMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (ParserConfigurationException ex) {
                Logger.getLogger(SchoolMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (TransformerException ex) {
                Logger.getLogger(SchoolMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return true;
    }
    
    @Override
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    @Override
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    @Override
    public void close(MessageContext context) {
    }
    
}

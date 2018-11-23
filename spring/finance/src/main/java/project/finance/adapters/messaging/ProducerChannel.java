package project.finance.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {
	
	// Topics
	String OPENED_INVOICE = "opened_invoice";
	
	// Channels
	@Output(OPENED_INVOICE)
	MessageChannel openInvoiceResult();
}

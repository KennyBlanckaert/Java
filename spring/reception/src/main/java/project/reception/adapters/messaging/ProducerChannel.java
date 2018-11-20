package project.reception.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {
	
	// Topics
	String ASSIGN_BED = "assign_bed";
	String CHECK_IN_COMPLETED = "check_in_completed";
	
	String OPEN_INVOICE = "open_invoice";
	
	// Channels to Ward
	@Output(ASSIGN_BED)
	MessageChannel assignBed();
	
	@Output(CHECK_IN_COMPLETED)
	MessageChannel checkInResult();
	
	
	// Channels to Finance
	@Output(OPEN_INVOICE)
	MessageChannel openInvoice();
}

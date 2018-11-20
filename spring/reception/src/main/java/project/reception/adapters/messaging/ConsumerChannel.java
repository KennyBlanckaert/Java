package project.reception.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel {

	// Topics
	String BED_ASSIGNED = "bed_assigned";
	
	String OPEN_INVOICE_RESULT = "open_invoice_result";
	
	// Channels to Ward
	@Input(BED_ASSIGNED)
	SubscribableChannel bedAssigned();
	
	// Channels to Finance
	@Input(OPEN_INVOICE_RESULT)
	public SubscribableChannel openInvoiceResult();
}

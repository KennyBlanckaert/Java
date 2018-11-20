package project.finance.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel {
	
	// Topics
	String OPEN_INVOICE = "open_invoice";
	String CHECK_IN_COMPLETED = "check_in_completed";
	
	// Channels
	@Input(OPEN_INVOICE)
	SubscribableChannel openInvoice();
	
	@Input(CHECK_IN_COMPLETED)
	SubscribableChannel hospitalStayCheckin();
}

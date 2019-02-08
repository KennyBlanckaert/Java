package project.reception.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel {

	// Topics
	String BED_ASSIGNED = "bed_assigned";
	
	// Channels to Ward
	@Input(BED_ASSIGNED)
	SubscribableChannel bedAssigned();
}

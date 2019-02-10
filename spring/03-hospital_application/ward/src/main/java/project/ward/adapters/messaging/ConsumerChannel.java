package project.ward.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel {
	
	// Topics
	String ASSIGN_BED = "assign_bed";
	String CHECK_IN_COMPLETE = "check_in_complete";
	
	// Channels
	@Input(ASSIGN_BED)
	SubscribableChannel assignBed();
	
	@Input(CHECK_IN_COMPLETE)
	SubscribableChannel checkInComplete();
}

package project.reception.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {
	
	// Topics
	String ASSIGN_BED = "assign_bed";
	String CHECK_IN_COMPLETE = "check_in_complete";
	
	// Channels
	@Output(ASSIGN_BED)
	MessageChannel assignBed();
	
	@Output(CHECK_IN_COMPLETE)
	MessageChannel checkInResult();
}

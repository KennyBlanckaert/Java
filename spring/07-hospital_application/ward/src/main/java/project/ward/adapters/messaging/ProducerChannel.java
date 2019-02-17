package project.ward.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {

	// Topics
	String BED_ASSIGNED = "bed_assigned";
	
	// Channels
	@Output(BED_ASSIGNED)
	MessageChannel bedAssigned();
}

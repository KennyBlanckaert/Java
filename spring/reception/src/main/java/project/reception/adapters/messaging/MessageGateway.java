package project.reception.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import project.reception.entities.HospitalStay;

@MessagingGateway
public interface MessageGateway {

	@Gateway(requestChannel = ProducerChannel.ASSIGN_BED)
	void assignBed(HospitalStay hospitalStay);
	
	@Gateway(requestChannel = ProducerChannel.CHECK_IN_COMPLETE)
	void checkInResult(HospitalStay hospitalStay);
}

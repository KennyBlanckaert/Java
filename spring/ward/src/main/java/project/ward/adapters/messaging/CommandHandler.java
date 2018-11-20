package project.ward.adapters.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import project.ward.entities.Bed;
import project.ward.entities.HospitalStay;
import project.ward.entities.HospitalStayStatus;
import project.ward.logic.WardService;

@Component
public class CommandHandler {
	
	@Autowired
	private WardService service;
	
	@StreamListener(ConsumerChannel.ASSIGN_BED)
	@SendTo(ProducerChannel.BED_ASSIGNED)
	public Bed assignBed(HospitalStay hospitalStay) {
		Bed bed = service.assignBed(hospitalStay);
		
		if (bed == null) {
			// Bed for Patient not connected to a ward
			return new Bed(hospitalStay.getPatientID());
		}
		
		return bed;
	}
	
	@StreamListener(ConsumerChannel.CHECK_IN_COMPLETE)
	public void checkInComplete(HospitalStay hospitalStay) {		
		// CheckIn is complete if succeed/failed
		if (hospitalStay.getStatus() == HospitalStayStatus.CHECK_IN_FAILED) {
			service.rollBack(hospitalStay);
		}
	}
}

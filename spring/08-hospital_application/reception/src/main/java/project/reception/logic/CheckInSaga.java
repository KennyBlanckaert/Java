package project.reception.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.reception.adapters.ReceptionRestController;
import project.reception.adapters.messaging.MessageGateway;
import project.reception.entities.HospitalStay;
import project.reception.entities.HospitalStayStatus;

@Component
public class CheckInSaga {
	
	@Autowired
	private MessageGateway gateway;
	
	@Autowired
	private ReceptionRestController controller;
	
	// Cleavage of the ReceptionService
	public void startPatientCheckIn(HospitalStay hospitalStay) {
		hospitalStay.setStatus(HospitalStayStatus.CHECK_IN_PENDING);
		gateway.assignBed(hospitalStay);
	}
	
	public void checkInFailed(HospitalStay hospitalStay) {
		hospitalStay.setStatus(HospitalStayStatus.CHECK_IN_FAILED);
		
		controller.performResponse(hospitalStay);
		gateway.checkInResult(hospitalStay);
	}

	public void checkInSuccesfull(HospitalStay hospitalStay, Long bedID) {
		hospitalStay.setBedID(bedID);
		hospitalStay.setStatus(HospitalStayStatus.CHECKED_IN);
		
		controller.performResponse(hospitalStay);
		gateway.checkInResult(hospitalStay);
	}
}

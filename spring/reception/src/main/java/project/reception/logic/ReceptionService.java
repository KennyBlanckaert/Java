package project.reception.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.reception.entities.HospitalStay;
import project.reception.persistence.HospitalStayRepository;

@Component
public class ReceptionService {
	
	@Autowired
	private HospitalStayRepository repository;
	
	@Autowired
	private CheckInSaga saga;
	
	// Service calls
    public List<HospitalStay> getHospitalStays() {
    	List<HospitalStay> result = new ArrayList<HospitalStay>();
    	repository.findAll().forEach(result::add);
    	return result;
    }
	
	public List<HospitalStay> getPatientBooking() {
		return repository.findByStatusBooked();				
	}

	public boolean checkInPatient(String patientID) {
		HospitalStay hospitalStay = repository.findByPatientID(patientID);
		if (hospitalStay != null) {
			if (hospitalStay.getBedID() == null) {
				saga.startPatientCheckIn(hospitalStay);
				repository.save(hospitalStay);
			}
			else { return false; }
		}
		else { System.out.println("hospitalStay is null"); }
		
		return true;
	}

	// Remote service calls
	public void failedToCheckInPatient(String patientID) {
		HospitalStay hospitalStay = repository.findByPatientID(patientID);
		
		saga.checkInFailed(hospitalStay);
		repository.save(hospitalStay);	
	}

	public void checkInSuccessful(String patientID, Long bedID) {
		HospitalStay hospitalStay = repository.findByPatientID(patientID);
		
		saga.checkInSuccesfull(hospitalStay, bedID);
		repository.save(hospitalStay);
	}
	
}

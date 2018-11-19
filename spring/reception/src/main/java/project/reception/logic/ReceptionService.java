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
	private HospitalStayRepository hospitalStayRepository;
	
	// Service calls
    public List<HospitalStay> getHospitalStays() {
    	List<HospitalStay> result = new ArrayList<HospitalStay>();
    	hospitalStayRepository.findAll().forEach(result::add);
    	return result;
    }
	
	public List<HospitalStay> getPatientBooking() {
		return hospitalStayRepository.findByStatusBooked();				
	}
	
}

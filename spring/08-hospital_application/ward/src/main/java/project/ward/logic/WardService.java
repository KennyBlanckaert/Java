package project.ward.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.ward.entities.Bed;
import project.ward.entities.HospitalStay;
import project.ward.entities.Ward;
import project.ward.persistence.WardRepository;

@Component
public class WardService {
	
	@Autowired
	private WardRepository repository;	
	
	// Service calls
	public List<Ward> getWards() {
		return repository.findAllWards();
	}

	public Ward getWardByID(Long id) {
		return repository.findWardById(id);
	}
	
	// Remote service calls
	public Bed assignBed(HospitalStay hospitalStay) {
		
		if (repository.countByIdAndBedsPatientIDIsNull(hospitalStay.getWardID()) == 0) {
			System.out.println("no beds available");
			return null;
		} 
		
		Ward ward = repository.findById(hospitalStay.getWardID()).get();
		Bed bed = ward.assignBedToPatient(hospitalStay.getPatientID());
		repository.save(ward);
		
		return bed;
	}

	public void rollBack(HospitalStay hospitalStay) {
		
		// Roll back assigned bed
		Ward ward = repository.findById(hospitalStay.getWardID()).get();
		for(Bed bed : ward.getBeds()) {
			if (bed.getPatientID() == hospitalStay.getPatientID()) {
				bed.setPatientID(null);
			}
		}
		
		repository.save(ward);
	}
}

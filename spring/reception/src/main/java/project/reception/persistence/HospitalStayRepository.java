package project.reception.persistence;

import project.reception.entities.*;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface HospitalStayRepository extends CrudRepository<HospitalStay, Long> {

	// Queries
	
	@Query("SELECT h FROM HospitalStay h WHERE status = project.reception.entities.HospitalStayStatus.BOOKED")
	List<HospitalStay> findByStatusBooked();
	
	@Query("SELECT h FROM HospitalStay h WHERE h.patientID = ?1")
	HospitalStay findByPatientID(String patientID);
}

package project.reception.persistence;

import project.reception.entities.*;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalStayRepository extends CrudRepository<HospitalStay, Long> {

	// Queries
	
	@Query("SELECT r FROM Reception r WHERE r.status = project.reception.entities.HospitalStayStatus.BOOKED")
	List<HospitalStay> findByStatusBooked();
	
	@Query("SELECT r FROM Reception r WHERE r.patientID = ?1")
	HospitalStay findByPatientID(String patientID);
}

package project.patient.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.patient.entities.*;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

	// Queries
	
	@Query("SELECT p FROM Patient p WHERE p.id = ?1")
	Patient findPatientById(Long id);
	
	@Query("SELECT p FROM Patient p WHERE p.firstname = ?1")
	List<Patient> findPatientByName(String firstname);
	
	@Query("SELECT p FROM Patient p WHERE p.firstname LIKE 'J%'")
	List<Patient> findPatientStartingWithJ();
	
	@Query("SELECT p FROM Patient p WHERE TIMESTAMPDIFF(YEAR, p.birth, NOW()) < 21")
	List<Patient> findPatientYoungerThan21();
}

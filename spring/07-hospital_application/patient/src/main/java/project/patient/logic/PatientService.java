package project.patient.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.patient.entities.Patient;
import project.patient.persistence.PatientRepository;

@Component
public class PatientService {
	
	@Autowired
	private PatientRepository repository;

	// Service calls
	public List<Patient> getPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		repository.findAll().forEach(patients::add);
		return patients;
	}

	public Patient getPatient(Long id) {
		return repository.findPatientById(id);
	}

	public List<Patient> getPatientsByFirstname(String firstname) {
		return repository.findPatientByName(firstname);
	}

	public List<Patient> getYoungPatients() {
		return repository.findPatientYoungerThan21();
	}

	public boolean addPatient(Patient patient) {
		repository.save(patient);
		return true;
	}	
}

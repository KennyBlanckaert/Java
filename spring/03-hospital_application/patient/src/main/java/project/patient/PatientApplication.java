package project.patient;

import java.time.LocalDate;
import java.time.Month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.patient.entities.Patient;
import project.patient.persistence.PatientRepository;

@SpringBootApplication
public class PatientApplication {
	
	private static final Logger log = LoggerFactory.getLogger(PatientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bean(PatientRepository repository) {
		return (args) -> {
			
			// save a couple of customers
			repository.save(new Patient(1l, "Jack", "Bauer", LocalDate.of(1996, Month.DECEMBER, 21)));
			repository.save(new Patient(2l, "Chloe", "O'Brian", LocalDate.of(1998, Month.JANUARY, 11)));
			repository.save(new Patient(3l, "Kim", "Bauer", LocalDate.of(2000, Month.JULY, 1)));
			repository.save(new Patient(4l, "David", "Palmer", LocalDate.of(2002, Month.JUNE, 15)));
			repository.save(new Patient(5l, "Michelle", "Dessler", LocalDate.of(2004, Month.AUGUST, 30)));

			/* For Testing */
			
			// fetch all patients
			log.info("Patients found with findAll():");
			log.info("-------------------------------");
			for (Patient patient : repository.findAll()) {
				log.info(patient.toString());
			}
			log.info("");

			// fetch by firstname
			log.info("Patient found with findPatientStartingWithJ():");
			log.info("--------------------------------------------");
			for (Patient patient : repository.findPatientStartingWithJ()) {
				log.info(patient.toString());
			}
			log.info("");
			
			// fetch by age
			log.info("Patient found with findPatientYoungerThan21():");
			log.info("--------------------------------------------");
			for (Patient patient : repository.findPatientYoungerThan21()) {
				log.info(patient.toString());
			}
			log.info("");
			
		};
	}
}

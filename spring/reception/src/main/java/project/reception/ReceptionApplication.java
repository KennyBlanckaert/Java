package project.reception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.reception.entities.HospitalStay;
import project.reception.persistence.HospitalStayRepository;

@SpringBootApplication
public class ReceptionApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ReceptionApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ReceptionApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(HospitalStayRepository repository) {
		
		return (args) -> {		
			
			// save a couple of HospitalStays
			repository.save(new HospitalStay("1", 1l, new Date()));
			repository.save(new HospitalStay("2", 2l, new Date()));
			repository.save(new HospitalStay("3", 3l, new Date()));

			// fetch all HospitalStays
			log.info("HospitalStay found with findAll():");
			for (HospitalStay hospitalStay : repository.findAll()) {
				log.info(hospitalStay.toString());
			}
			log.info("-------------------------------");
			
			// fetch single booked HospitalStay for patient
			log.info("HospitalStay found with findByPaitentIdAndStatusBooked():");
			for (HospitalStay hospitalStay : repository.findByStatusBooked()) {
				log.info(hospitalStay.toString());
			}
			log.info("-------------------------------");						
		};
	}
}

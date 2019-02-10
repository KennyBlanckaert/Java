package project.reception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import project.reception.adapters.messaging.ConsumerChannel;
import project.reception.adapters.messaging.MessageGateway;
import project.reception.adapters.messaging.ProducerChannel;
import project.reception.entities.HospitalStay;
import project.reception.persistence.HospitalStayRepository;

@SpringBootApplication
@EnableBinding({ProducerChannel.class, ConsumerChannel.class})
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
			repository.save(new HospitalStay("2", 1l, new Date()));
			repository.save(new HospitalStay("3", 1l, new Date()));
			repository.save(new HospitalStay("4", 4l, new Date()));

			// fetch all HospitalStays
			log.info("HospitalStay found with findAll():");
			for (HospitalStay hospitalStay : repository.findAll()) {
				log.info(hospitalStay.toString());
			}
			log.info("-------------------------------");
			
			// fetch one hospitalStay
			log.info("HospitalStay found with findByPatientID():");
			log.info(repository.findByPatientID("1").toString());
			
			// fetch single booked HospitalStay for patient
			log.info("HospitalStay found with findByPatientIdAndStatusBooked():");
			for (HospitalStay hospitalStay : repository.findByStatusBooked()) {
				log.info(hospitalStay.toString());
			}
			log.info("-------------------------------");						
		};
	}
}

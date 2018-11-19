package project.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.finance.entities.Invoice;
import project.finance.persistence.FinanceRepository;

@SpringBootApplication
public class FinanceApplication {
	
	private static final Logger log = LoggerFactory.getLogger(FinanceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bean(FinanceRepository repository) {
		return (args) -> {
			
			repository.deleteAll();
			
			// save a couple of invoices
			Invoice invoice = new Invoice(1l, 10l);
			invoice.addItem("item1", 20.30);
			invoice.paid();
			repository.save(invoice);
			
			invoice = new Invoice(2l, 11l);
			invoice.addItem("item2", 5.30);
			invoice.paid();
			repository.save(invoice);
			
			invoice = new Invoice(2l, 12l);
			invoice.addItem("item3", 5.30);
			repository.save(invoice);			

			invoice = new Invoice(3l, 13l);
			invoice.addItem("item1", 20.30);
			invoice.paid();
			repository.save(invoice);

			log.info("-------------------------------");
			// fetch all invoices
			log.info("Invoices found with findAll():");
			for (Invoice found : repository.findAll()) {
				log.info(found.toString());
			}
			log.info("-------------------------------");
			
			// fetch all invoice containing item1
			log.info("Invoices found with findByName():");
			for (Invoice found : repository.findByName("item1")) {
				log.info(found.toString());
			}
			log.info("-------------------------------");
			
			// fetch all invoice containing an item with a cost greater than 10
			log.info("Invoices found with findByCostGreaterThan():");
			for (Invoice found : repository.findByCostGreaterThan(10)) {
				log.info(found.toString());
			}
			log.info("-------------------------------");
			
			// fetch all invoices not paid for patient 2
			log.info("Invoices found with findByStatusNotPaid():");
			for (Invoice found : repository.findByStatusNotPaid()) {
				log.info(found.toString());
			}
			log.info("-------------------------------");
		};
	}
}

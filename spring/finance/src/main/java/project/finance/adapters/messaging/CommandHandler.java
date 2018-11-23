package project.finance.adapters.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import project.finance.entities.HospitalStay;
import project.finance.entities.Invoice;
import project.finance.logic.FinanceService;

@Component
public class CommandHandler {

	@Autowired
	FinanceService service;

	@StreamListener(value = ConsumerChannel.OPEN_INVOICE)   
	@SendTo(ProducerChannel.OPENED_INVOICE)
	public Invoice openInvoice(HospitalStay hospitalStay) {
		System.out.println("Finance OPEN_INVOICE recieved: " + hospitalStay);
		 
		return service.openInvoice(hospitalStay);		
	}
	
	@StreamListener(ConsumerChannel.CHECK_IN_COMPLETED)
	public void patientCheckedIn(HospitalStay hospitalStay) {
		System.out.println("Finance HOSPITAL_STAY_CHECKED_IN recieved: " + hospitalStay);
	}
	
	
}

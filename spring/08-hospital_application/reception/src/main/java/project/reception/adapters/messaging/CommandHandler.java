package project.reception.adapters.messaging;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.reception.entities.Bed;
import project.reception.logic.ReceptionService;

@Component
public class CommandHandler {
	
	@Autowired
	private ReceptionService service;

	@StreamListener(ConsumerChannel.BED_ASSIGNED)
	public void bedAssigned(String json) {
		System.out.println("Reception BED_ASSIGNED result: " + json);
		
		Bed bed = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			bed = mapper.readerFor(Bed.class).readValue(json);
			
			if (bed.getWard() != null) {
				System.out.println("assigned with success");
				service.checkInSuccessful(bed.getPatientID(), bed.getId());
			}
			else {
				System.out.println("assigned with failure");
				service.failedToCheckInPatient(bed.getPatientID());
			}
		}
		catch (JsonMappingException e) { e.printStackTrace(); } 
		catch (JsonParseException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
	}
}

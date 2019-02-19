package project.reception.adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import project.reception.entities.HospitalStay;
import project.reception.logic.ReceptionService;


@RestController
public class ReceptionRestController {
	
	@Autowired
	private ReceptionService service;
	
	Map<String, DeferredResult<HospitalStay>> deferredResults = new HashMap<String, DeferredResult<HospitalStay>>();
	
	// Rest calls
	@RequestMapping(value="/reception", method=RequestMethod.GET)
    public List<HospitalStay> getHospitalStays() {
        return service.getHospitalStays();
    }
	
	@RequestMapping(value="/reception/get_patient_booking", method=RequestMethod.GET)
	public List<HospitalStay> getPatientBooking() {			
		return service.getPatientBooking();
	}
	
	@RequestMapping(value="/reception/check_in_patient")
	public DeferredResult<HospitalStay> handlePatientCheckIn(@RequestParam("id") String patientID) {		
		DeferredResult<HospitalStay> deferredResult = new DeferredResult<>(10000l); 
		deferredResult.onTimeout(() ->  deferredResult.setErrorResult("Request timeout occurred."));

		deferredResults.put(patientID, deferredResult); 
		
		try {
			if (!service.checkInPatient(patientID)) {
				deferredResult.setErrorResult("Patient already has a bed!");
			}
		} 
		catch (Exception e) {
			deferredResults.remove(patientID);
			deferredResult.setErrorResult("Failed to check-in patient. " + e.getMessage());
		}
		
		return deferredResult;
	}

	public void performResponse(HospitalStay response) {
		DeferredResult<HospitalStay> deferredResult = deferredResults.remove(response.getPatientID());
		
		if (response.getBedID() == null) {
			deferredResult.setErrorResult("No beds available");
			return;
		}
		
		if ((deferredResult != null) && !deferredResult.isSetOrExpired()) {
			deferredResult.setResult(response);
			return;
		}
	}
}


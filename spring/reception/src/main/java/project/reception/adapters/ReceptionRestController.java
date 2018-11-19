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
	ReceptionService reception;
	
	@RequestMapping(value="/reception/hospital_stays", method=RequestMethod.GET)
    public List<HospitalStay> getHospitalStays() {
        return reception.getHospitalStays();
    }
	
	@RequestMapping(value="/reception/get_patient_booking", method=RequestMethod.GET)
	public List<HospitalStay> getPatientBooking() {			
		return reception.getPatientBooking();
	}
}


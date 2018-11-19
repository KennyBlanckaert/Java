package project.patient.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.patient.logic.PatientService;
import project.patient.entities.*;

@RestController
public class PatientRestController {
	
	@Autowired
	private PatientService service;
	
	// Rest calls
    @RequestMapping(value="/patients", method=RequestMethod.GET)
    public List<Patient> getPatients() {
        return service.getPatients();
    }
    
    @RequestMapping(value="/patients/{id}", method=RequestMethod.GET)
    public Patient getPatient(@PathVariable("id") Long id) {
    	return service.getPatient(id);
    }
    
    @RequestMapping(value="/patients/search", method=RequestMethod.GET)
    public List<Patient> getPatientsByFirstname(@RequestParam("name") String firstname) {
    	return service.getPatientsByFirstname(firstname);
    }
    
    @RequestMapping(value="/patients/youth", method=RequestMethod.GET)
    public List<Patient> getYoungPatients() {
    	return service.getYoungPatients();
    }
    
    @RequestMapping(value="/patients/add", method=RequestMethod.PUT)
    public boolean addPatient(@RequestBody Patient patient) {
    	return service.addPatient(patient);
    }
}

package project.ward.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.ward.entities.Ward;
import project.ward.logic.WardService;

@RestController
public class WardRestController {

	@Autowired
	private WardService service;
	
	// Rest calls
	@RequestMapping(value="/wards", method=RequestMethod.GET)
	public List<Ward> getWards() {
		return service.getWards();
	}
	
	@RequestMapping(value="/wards/{id}", method=RequestMethod.GET)
	public Ward getWardByID(@PathVariable("id") Long id) {
		return service.getWardByID(id);
	}
}

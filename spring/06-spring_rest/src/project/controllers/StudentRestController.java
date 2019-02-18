package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.entities.Student;
import project.errors.StudentNotFoundException;
import project.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentRestController {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/")
	public List<Student> getStudents() {
		return service.getStudents();
	}
	
	@GetMapping("/name/{firstname}")
	public List<Student> getStudentsByName(@PathVariable("firstname") String firstname) {
		return service.getStudentsByName(firstname);
	}
	
	@GetMapping("/id/{id}")
	public Student getStudentsByName(@PathVariable("id") int id) {
			
		Student student = service.getStudentsById(id);
		if (student == null) {
			throw new StudentNotFoundException("student with id " + id + " does not exist");
		}
		
		return student;
	}
}

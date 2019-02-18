package project.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;

import project.entities.Student;

@Component
public class StudentService {
	
	private List<Student> students;
	
	@PostConstruct
	public void init() {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		File file = new File(getClass().getClassLoader().getResource("student.json").getFile());
			this.students = Arrays.asList(mapper.readValue(file, Student[].class));
    	} 
    	catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public List<Student> getStudents() {
		return this.students;
	}

	public List<Student> getStudentsByName(String firstname) {
		return this.students.stream().filter(student -> student.getFirstName().equalsIgnoreCase(firstname)).collect(Collectors.toList());
	}

	public Student getStudentsById(int id) {
		Optional<Student> result = this.students.parallelStream().filter(student -> student.getStudentNumber() == id).findFirst();
		return result.orElse(null);
	}
}

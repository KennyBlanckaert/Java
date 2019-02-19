package test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.entities.Student;


public class JsonReader {
	
	public List<Student> readJson() {
		
		List<Student> students = null;
		ObjectMapper mapper = new ObjectMapper();
    	try {
    		File file = new File(getClass().getClassLoader().getResource("student.json").getFile());
			students = Arrays.asList(mapper.readValue(file, Student[].class));
    	} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return students;
	}
}

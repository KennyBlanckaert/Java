package test;

import java.util.List;

import project.entities.Student;

public class Main {
	
	public static void main(String[] args) {
		JsonReader reader = new JsonReader();
		List<Student> students = reader.readJson();
		for (Student student : students) {
			System.out.println(student);
		}
	}
}

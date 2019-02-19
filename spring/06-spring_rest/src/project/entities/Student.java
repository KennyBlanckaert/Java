package project.entities;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Student {

	// Fields
	private int studentNumber;
	private String firstName;
	private String lastName;
	private Address address;
	private String[] courses;
	
	// Constructor
	public Student() {
		
	}

	// Getters
	public int getStudentNumber() {
		return studentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}
	
	public String[] getCourses() {
		return courses;
	}

	// Setters
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	
	// Functions
	@Override
	public String toString() {
		return "Student [studentNumber=" + studentNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", courses=" + Arrays.toString(courses) + "]";
	}
}

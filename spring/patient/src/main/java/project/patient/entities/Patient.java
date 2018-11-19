package project.patient.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ssn;
	private String firstname;
	private String lastname;
	private LocalDate birth;
	
	// Constructors
	protected Patient() { }
	
	public Patient(long ssn, String firstname, String lastname, LocalDate birth) {
		this.ssn = ssn;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birth = birth;
	}

	// Getters
	public long getSsn() {
		return ssn;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public LocalDate getBirth() {
		return birth;
	}

	// Setters
	public void setSsn(long ssn) {
		this.ssn = ssn;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}	
	
	// toString-method
    @Override
    public String toString() {
        return String.format("Patient[id=%d, firstname='%s', lastname='%s']", this.ssn, this.firstname, this.lastname);
    }
}

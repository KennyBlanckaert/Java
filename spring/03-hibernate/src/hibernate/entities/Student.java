package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")	
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column("id")
	private Integer id;
	
	//@Column("firstname")
	private String firstname;
	
	//@Column("lastname")
	private String lastname;
	
	// Default constructor
	public Student() { }
	
	// Constructor
	public Student(String firstname, String lastname) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	// Getters
	public Integer getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	// Setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	// Functions
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	
	
	
}

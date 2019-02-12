package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name="student")	only when name is different
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column("id")
	private Integer id;
	
	//@Column("firstname")
	private String firstname;
	
	//@Column("lastname")
	private String lastname;
	
	// Constructor
	public Student(String firstname, String lastname) {
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

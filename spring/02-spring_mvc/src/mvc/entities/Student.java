package mvc.entities;

public class Student {

	// Fields
	private String firstName;
	private String lastName;
	private String country;
	private String session;
	
	// Constructor
	public Student() {
		
	}

	// Getters
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getSession() {
		return this.session;
	}

	// Setters
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setSession(String session) {
		this.session = session;
	}
}

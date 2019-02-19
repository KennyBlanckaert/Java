package aop.entities;

public class Account {
	
	// Fields
	private String name;
	private String status;
	
	// Constructor
	public Account() {
		this.status = "offline";
	}
	
	// Getters
	public String getName() {
		return name;
	}
	public String getStatus() {
		return status;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

package project.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Address {

	// Fields
	private String country;
	private String city;
	private int zip;
	private String street;
	
	// Constructor
	public Address() {
	
	}

	// Getters
	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public int getZip() {
		return zip;
	}

	public String getStreet() {
		return street;
	}

	
	// Setters
	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	// Functions
	@Override
	public String toString() {
		return "Address [country=" + country + ", city=" + city + ", zip=" + zip + ", street=" + street + "]";
	}
	
	
}

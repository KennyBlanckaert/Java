package project.reception.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Ward {
	
	// Fields
	@Id 
	@GeneratedValue
	Long id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="ward", fetch=FetchType.EAGER)
	List<Bed> beds;
	
	// Constructors
	protected Ward(){ 
		beds = new ArrayList<Bed>();
	}
	
	public Ward(int numberOfBeds){
		beds = new ArrayList<Bed>();
		for (int i = 0; i < numberOfBeds; i++) {
			beds.add(new Bed(this));
		}
	}
	
	// Getters
	public Long getID() {
		return id;
	}
	
	public List<Bed> getBeds() {
		return beds;
	}
	
	// Setters
	public void setID(Long id) {
		this.id = id;
	}

	public void setBeds(List<Bed> beds) {
		this.beds = beds;
	}
	
	// Functions
	public Bed assignBedToPatient(String patientId) { 
		for(Bed bed : beds) {
			if(bed.getPatientID() == null) {
				bed.setPatientID(patientId);
				return bed;
			}
		}
		return null;
	}
	
	@Override
    public String toString() {
        return String.format("ward[id=%d, beds='%s']", this.id, this.beds);
    }
}

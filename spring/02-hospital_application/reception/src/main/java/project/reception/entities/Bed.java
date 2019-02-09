package project.reception.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Bed {
	
	// Fields
	@Id 
	@GeneratedValue
	Long bedID;
	
	String patientID = null;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ward_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Ward ward;
	
	// Constructors
	protected Bed() {}
	
	public Bed(Ward ward) {
		this.ward = ward;
	}
	
	public Bed(String patientID) {
		this.patientID = patientID;
	}
	
	// Getters
	public Long getId() {
		return bedID;
	}
	
	public Ward getBed() {
		return ward;
	}	
	
	public String getPatientID() {
		return patientID;
	}
	
	public Ward getWard() {
		return ward;
	}

	// Setters
	public void setId(Long bedID) {
		this.bedID = bedID;
	}

	public void setBed(Ward ward) {
		this.ward = ward;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}
	
	// Functions
	@Override
    public String toString() {
        return String.format("bed[id=%d, occupied='%s']", this.bedID, this.patientID);
    }

}

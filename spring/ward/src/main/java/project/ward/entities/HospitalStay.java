package project.ward.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/* Limited functionality of the entity in reception */
@Entity
public class HospitalStay {

	// Fields
	@Id 
	@GeneratedValue
	private Long id;		
	private String patientID;	
	private Long wardID;	
	private Long bedID;	
	private Date date;	
	private HospitalStayStatus status;
	
	// Constructors
	protected HospitalStay() {}
	
	// Getters
	public Long getID() {
		return id;
	}
	
	public String getPatientID() {
		return patientID;
	}	
	
	public Long getWardID() {
		return wardID;
	}
	
	public Date getDate() {
		return date;
	}
		
	public Long getBedID() {
		return bedID;
	}
	
	public HospitalStayStatus getStatus() {
		return this.status;
	}
	
	// Setters
	public void setID(Long id) {
		this.id = id;
	}

	public void setPatientID(String patientId) {
		this.patientID = patientId;
	}

	public void setWardID(Long wardId) {
		this.wardID = wardId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setBedID(Long bedID) {
		this.bedID = bedID;
	}
	
	public void setStatus(HospitalStayStatus status) {
		this.status = status;
	}

	// Functions
	@Override
    public String toString() {
        return String.format(
                "HospitalStay[id=%s, patientId='%s', wardId='%d', bedId='%d', dateOfStay='%s']",
                this.id, this.patientID, this.wardID, this.bedID, this.date);
    }
}

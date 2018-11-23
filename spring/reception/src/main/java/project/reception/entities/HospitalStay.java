package project.reception.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	public HospitalStay(String patientID, Long wardID, Date date) {
		this.patientID = patientID;
		this.wardID = wardID;
		this.date = date;
		
		status = HospitalStayStatus.BOOKED;
	}
	
	// Getters
	public Long getID() {
		return id;
	}
	
	public String getPatientID() {
		return patientID;
	}
	
	public void setID(Long id) {
		this.id = id;
	}
	
	public Long getWardID() {
		return wardID;
	}
	
	public Date getDate() {
		return date;
	}

	public void setPatientID(String patientId) {
		this.patientID = patientId;
	}
	
	public Long getBedID() {
		return bedID;
	}


	// Setters
	public void setWardID(Long wardId) {
		this.wardID = wardId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public HospitalStayStatus getStatus() {
		return status;
	}

	public void setStatus(HospitalStayStatus status) {
		this.status = status;
	}

	public void setBedID(Long bedID) {
		this.bedID = bedID;
	}

	// Functions
	@Override
    public String toString() {
        return String.format(
                "HospitalStay[id=%s, patientId='%s', wardId='%d', bedId='%d', dateOfStay='%s', status='%s']",
                this.id, this.patientID, this.wardID, this.bedID, this.date, this.status);
    }
}

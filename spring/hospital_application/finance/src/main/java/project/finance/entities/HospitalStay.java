package project.finance.entities;

/**
 * The HospitalStay class should only contain fields relevant to the Finance application.
 *   This information should not be saved in this service - it is available from the Reception service.
 * @author student
 *
 */
public class HospitalStay {

	// Fields
	private Long id;
	private String patientID;
	private String status;
	
	
	// Getters
	public String getPatientID() {
		return patientID;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Long getId() {
		return id;
	}
	
	// Setters
	public void setPatientId(String patientID) {
		this.patientID = patientID;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	// Functions
	@Override
    public String toString() {
        return String.format("HospitalStay[id='%d', patientId='%s', status='%s']", this.id, this.patientID, this.status);
    }
}

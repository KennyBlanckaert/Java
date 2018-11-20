package project.finance.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Invoice {

	// Fields
	@Id
	private String id;
	private String patientID;
	private Long hospitalStayID;
	private LocalDate created;
	private Status status;
	private List<InvoiceItem> items;
	
	// Constructors
	protected Invoice() { }
	
	public Invoice(String patientID, Long hospitalStayID) {
		this.patientID = patientID;
		this.hospitalStayID = hospitalStayID;
		this.created = LocalDate.now();
		this.status = Status.Open;
		items = new ArrayList<InvoiceItem>();
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getPatientID() {
		return patientID;
	}

	public Long getHospitalStayID() {
		return hospitalStayID;
	}

	public LocalDate getCreated() {
		return created;
	}

	public Status getStatus() {
		return status;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public void setHospitalStayID(Long hospitalStayID) {
		this.hospitalStayID = hospitalStayID;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}
	
	// Functions
	public void addItem(String name, double cost) {
		if (this.status == Status.Open) {
			this.items.add(new InvoiceItem(name, cost));
		}
	}
	
	@Override
	public String toString() {
        return 
        	String.format("Invoice[id=%s, patientId='%s', hospitalStayId='%d', creationDate='%s', status='%s', items='%s']",
        		this.id, this.patientID, this.hospitalStayID, this.created, this.status, this.items.toString());
	}
	
	public void open() {
		this.status = Status.Open;
	}
	
	public void paid() {
		this.status = Status.Paid;
	}
	
	public void closed() {
		this.status = Status.Closed;
	}
	
}

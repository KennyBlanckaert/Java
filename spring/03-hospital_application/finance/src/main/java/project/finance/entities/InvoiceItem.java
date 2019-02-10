package project.finance.entities;

public class InvoiceItem {

	// Fields
	private String name;
	private double cost;
	
	// Constructors
	protected InvoiceItem() { }
	
	public InvoiceItem(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}

	// Getters
	public String getName() {
		return name;
	}

	public double getCost() {
		return cost;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	// Functions
	@Override
    public String toString() {
        return String.format("InvoiceItem[name='%s', cost='%.2f']", this.name, this.cost);
    }
}

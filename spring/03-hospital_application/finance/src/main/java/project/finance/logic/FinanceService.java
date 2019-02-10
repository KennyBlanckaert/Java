package project.finance.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.finance.entities.HospitalStay;
import project.finance.entities.Invoice;
import project.finance.entities.Status;
import project.finance.persistence.FinanceRepository;

@Component
public class FinanceService {

	@Autowired
	private FinanceRepository repository;

	// Service calls
	public List<Invoice> getInvoices() {
		return repository.findAll();
	}

	public List<Invoice> getInvoicesByCost(double cost) {
		return repository.findByCostGreaterThan(cost);
	}

	public List<Invoice> getInvoicesByStatusNotPaid() {
		return repository.findByStatusNotPaid();
	}
}

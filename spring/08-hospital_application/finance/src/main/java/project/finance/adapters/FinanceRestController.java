package project.finance.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.finance.entities.Invoice;
import project.finance.logic.FinanceService;

@RestController
public class FinanceRestController {

	@Autowired
	private FinanceService service;
	
	// Rest calls
	@RequestMapping(value="/finance", method=RequestMethod.GET)
	public List<Invoice> getInvoices() {
		return service.getInvoices();
	}
	
	@RequestMapping(value="/finance/search", method=RequestMethod.GET)
	public List<Invoice> getInvoicesByCost(@RequestParam("cost") double cost) {
		return service.getInvoicesByCost(cost);
	}
	
	@RequestMapping(value="/finance/unpaid", method=RequestMethod.GET)
	public List<Invoice> getInvoicesByStatusNotPaid() {
		return service.getInvoicesByStatusNotPaid();
	}
}

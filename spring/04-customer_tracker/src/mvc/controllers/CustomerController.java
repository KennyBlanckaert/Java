package mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.entities.Customer;
import mvc.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		List<Customer> customers = service.getCustomers();
		model.addAttribute("customers", customers);
		
		return "customerList";
	}
	
	@GetMapping("/customerForm")
	public String customerForm(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "addCustomerForm";
	}
	
	@PostMapping("/addCustomer")
	public String addCustomer(@ModelAttribute("customer") Customer customer, Model model) {	
		
		service.addCustomer(customer);
		
		return "redirect:/customer/list";
	}
}

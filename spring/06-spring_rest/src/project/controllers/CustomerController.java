package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.entities.Customer;
import project.errors.CustomerNotFoundException;
import project.service.CustomerService;

// Possibility to secure mapping with Spring Security (Authorization)
@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	// Select
	@GetMapping
	public List<Customer> getCustomers() {
		return service.getCustomers();
	}
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable("id") int id) {
		Customer customer = service.getCustomer(id);
		if (customer == null) {
			throw new CustomerNotFoundException("customer with id " + id + " does not exist");
		}
		
		return customer;
	}
	
	// Create
	@PutMapping
	public void addCustomer(@RequestBody Customer customer) {
		service.addCustomer(customer);
	}
	
	// Replace - Update
	@PostMapping
	public void updateCustomer(@RequestBody Customer customer) {
		service.updateCustomer(customer);
	}
	
	// Delete
	@PostMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") int id) {
		service.deleteCustomer(id);
	}
}

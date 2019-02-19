package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.dao.CustomerDAO;
import project.entities.Customer;

@Service
public class CustomerServiceImplementation implements CustomerService {
	
	@Autowired 
	private CustomerDAO dao;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return dao.getCustomers();
	}
	
	@Override
	@Transactional
	public Customer getCustomer(Integer id) {
		return dao.getCustomer(id);
		
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		dao.addCustomer(customer);
		
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		dao.updateCustomer(customer);
		
	}

	@Override
	@Transactional
	public void deleteCustomer(Integer id) {
		dao.deleteCustomer(id);
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String keyword) {
		return dao.searchCustomers(keyword);
	}
}

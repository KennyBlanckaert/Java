package mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mvc.dao.CustomerDAO;
import mvc.entities.Customer;

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
		Customer customer = dao.getCustomer(id);
		return customer;
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
}

package project.dao;

import java.util.List;

import project.entities.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void addCustomer(Customer customer);

	public Customer getCustomer(Integer id);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Integer id);

	public List<Customer> searchCustomers(String keyword);
}

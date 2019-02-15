package mvc.dao;

import java.util.List;

import mvc.entities.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void addCustomer(Customer customer);

	public Customer getCustomer(Integer id);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Integer id);
}

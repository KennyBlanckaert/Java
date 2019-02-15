package mvc.service;

import java.util.List;
import mvc.entities.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void addCustomer(Customer customer);
}

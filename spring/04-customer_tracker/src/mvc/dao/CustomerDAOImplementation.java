package mvc.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mvc.entities.Customer;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> result = session.createQuery("FROM Customer ORDER BY firstname", Customer.class);
		List<Customer> customers = result.getResultList();
		
		return customers;
	}
	
	@Override
	public Customer getCustomer(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@Override
	public void addCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.save(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query = session.createQuery("DELETE FROM Customer WHERE id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> result = null;
		if (keyword != null && keyword.trim().length() > 0) {
			result = session.createQuery("FROM Customer WHERE lower(firstname) LIKE :searchString OR lower(lastname) like :searchString", Customer.class);
			result.setParameter("searchString", "%" + keyword.toLowerCase() + "%");
        }
        else {
        	result = session.createQuery("FROM Customer", Customer.class);            
        }
        List<Customer> customers = result.getResultList();
		
		return customers;
	}
}

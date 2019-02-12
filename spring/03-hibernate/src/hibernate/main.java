package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entities.Student;

public class main {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			Student student = new Student("hibernate", "student");
			
			// Write
			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
			
			// Read examples
			session.createQuery("FROM students").list();
			session.createQuery("FROM students s WHERE s.firstname LIKE 'Mon%'").list();
			session.createQuery("FROM students s WHERE s.lastname = 'Luffy'").list();
			
			System.out.println("Done!");
			
			/* PS:
			 * 		queries are show on stdout because of the show_sql property in the hibernate.cfg.xml file
			 * 		anyway, when adding an object to the database, this doesn't print the actual values but question marks.
			 * 		To actually see the added values, you can make use of log4j (see the .properties file).
			 * 		This configuration file contains the TRACE option for low-level tracing
			 * */
		}
		catch(Exception e) {
			e.printStackTrace();
			session.close();
			factory.close();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}

package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entities.Student;

public class crud {

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
			
			/* More examples (Session/EntityManager is closed once committed)	
			 
				// Read examples
				student = session.get(Student.class, 12);
				session.createQuery("FROM Student").list();
				session.createQuery("FROM Student s WHERE s.firstname LIKE 'Mon%'").list();
				session.createQuery("FROM Student s WHERE s.lastname = 'Luffy'").list();
				
				// Update examples
				session.beginTransaction();
				student.setLastname("example");
				session.save(student);
				session.getTransaction().commit();
				
				session.createQuery("UPDATE Student s SET s.description = 'student from UGent'").executeUpdate();
				
				// Delete example
				session.beginTransaction();
				student = session.get(Student.class, 12);
				session.delete(student);
				session.getTransaction().commit();
				
				session.createQuery("DELETE FROM Student s WHERE s.id = 12").executeUpdate();
			*/
				
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

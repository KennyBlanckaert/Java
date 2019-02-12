package hibernate;

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
			System.out.println("Start!");
			Student student = new Student("hibernate", "student");
			System.out.println("1!");
			session.beginTransaction();
			System.out.println("2!");
			session.save(student);
			System.out.println("3!");
			session.getTransaction().commit();
			
			System.out.println("Done!");
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

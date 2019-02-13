package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entities.Course;
import hibernate.entities.Student;
import hibernate.entities.Teacher;

// @OneToOne & @JoinColumn annotations
public class oneToOneRelationShip {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Teacher.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			Course course = new Course("Spring");
			Teacher teacher = new Teacher("Bonita", "Applebum");
			course.setTeacher(teacher);
			
			session.beginTransaction();
			session.save(course);				
			session.getTransaction().commit();
			
			// Note: deletion of a course, will also delete the teacher (as a result of the cascading type)
			
			System.out.println("Done");
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

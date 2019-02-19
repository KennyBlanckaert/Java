package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entities.Course;
import hibernate.entities.Student;
import hibernate.entities.Teacher;

// @OneToOne & @JoinColumn annotations
// This code is commented in the entity classes, but should also work just fine with the 1:m & m:1 relationship implementations
public class oneToOneRelationShip {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Teacher.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Course course = new Course("Spring");
			session.save(course);
			Teacher teacher = new Teacher("Bonita", "Applebum");
			session.save(teacher);
			
			course.setTeacher(teacher);
			session.save(course);	
			
			session.getTransaction().commit();
			
			// Note: deletion of a course, will also delete the teacher when CascadingType = All
			//		 other CascadingType require the relationship to be set to null
			
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

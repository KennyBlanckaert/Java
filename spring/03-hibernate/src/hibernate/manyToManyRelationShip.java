package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.entities.Course;
import hibernate.entities.Student;
import hibernate.entities.Teacher;

/* @ManyToOne & @OneToMany & @JoinColumn annotations
 * 
 * (loading default settings)
 * @OneToOne --- eager loading
 * @ManyToOne --- eager loading
 * @OneToMany --- lazy loading
 * @ManyToMany --- lazy loading		
 */
public class manyToManyRelationShip {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Teacher.class)
								.buildSessionFactory();
		
		// Session must be open continuously while using lazy loading (error is thrown otherwise)
		// To use data while session is closed, it must be loaded already when session was open
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Course course = new Course("hibernate");
			session.save(course);
			
			Student student = new Student("Kenny", "Blanckaert");
			student.addCourse(course);
			session.save(student);
			
			session.getTransaction().commit();
			
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

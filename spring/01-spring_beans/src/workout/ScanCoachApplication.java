package workout;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import workout.entities.coach.Coach;

public class ScanCoachApplication {

	public static void main(String[] args) {
		
		// Automatic creation of beans in 'scanApplicationContext.xml'
		// This makes use of @Component for beans, @Autowired for injection & @Value for properties
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("scanApplicationContext.xml");
		Coach coach = context.getBean("footballCoach", Coach.class);
		
		System.out.println(coach.welcome());
		System.out.println(coach.getWorkout());
		System.out.println(coach.getFortune());
		
		context.close();
	}
}

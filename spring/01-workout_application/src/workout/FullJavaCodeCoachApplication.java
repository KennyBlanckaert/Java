package workout;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;  

import workout.entities.coach.Coach;

public class FullJavaCodeCoachApplication {
	
	// Creation of beans using only Java code
	public static void main(String[] args) {	
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaApplicationContext.class);
		Coach coach = context.getBean("footballCoach", Coach.class);
		
		System.out.println(coach.welcome());
		System.out.println(coach.getWorkout());
		System.out.println(coach.getFortune());
		
		context.close();
	}
}

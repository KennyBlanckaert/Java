

import org.springframework.context.support.ClassPathXmlApplicationContext;

import entities.coach.Coach;

public class CoachApplication {
	
	public static void main(String[] args) {	
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Coach coach = context.getBean("myCoach", Coach.class);
		
		System.out.println(coach.welcome());
		System.out.println(coach.getWorkout());
		System.out.println(coach.getFortune());
		
		context.close();
	}
}

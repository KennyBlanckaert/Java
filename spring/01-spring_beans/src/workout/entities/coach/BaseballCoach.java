package workout.entities.coach;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import workout.entities.service.FortuneService;

@Component
//@Scope("prototype")
public class BaseballCoach implements Coach {

	// Fields
	@Value("${coach.name}")
	private String name;
	
	@Value("${coach.age}")
	private int age;
	
	// Field Injection
	@Autowired
	private FortuneService fortuneService;
	
	// Default constructor
	public BaseballCoach() {
		
	}
	
	// Constructor Injection (default since Spring 4.3: when annotation is left out and there is only one constructor, this is used automatically)
	// Recommended over Setter Injection: dependencies can be final (robust and thread-safe)
	// Be consistent in usage
	@Autowired
	public BaseballCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	// Getters
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public FortuneService getFortuneService() {
		return fortuneService;
	}
	
	// Setter Injection
	@Autowired
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	// Functions
	@Override
	public String getWorkout() {
		return "Spend 40 minutes on batting practice";
	}

	@Override
	public String getFortune() {
		return "Your boost: " + fortuneService.getFortune();
	}

	@Override
	public String welcome() {
		return "Hello! I'm " + this.name + ", your trainer for today.";
	}

	// Lifecyle functions
	@PostConstruct
	public void onStartup() {
		System.out.println("Starting the application");
	}

	@PreDestroy
	public void onDestroy() {
		System.out.println("Terminating...");
	}
}

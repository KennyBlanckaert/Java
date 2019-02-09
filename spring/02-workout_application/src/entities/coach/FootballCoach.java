package entities.coach;

import entities.service.FortuneService;

public class FootballCoach implements Coach {

	// Fields
	private String name;
	private int age;
	private FortuneService fortuneService;
	
	// Default Constructor
	public FootballCoach() {
		
	}
	
	// Constructor Injection
	public FootballCoach(FortuneService fortuneService) {
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
	
	// Setters
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// Functions
	@Override
	public String getWorkout() {
		return "Play a 30 minute match against another player";
	}

	@Override
	public String getFortune() {
		return "Your boost: " + fortuneService.getFortune();
	}

	@Override
	public String welcome() {
		return "Hello! I'm " + this.name + ", your trainer for today.";
	}
}

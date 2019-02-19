package workout.entities.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HappyFortuneService implements FortuneService {
	
	// Fields
	@Value("${workout.fortunes}")
	private String[] fortunes;
	
	// Getters
	public String[] getFortunes() {
		return fortunes;
	}

	// Setters
	public void setFortunes(String[] fortunes) {
		this.fortunes = fortunes;
	}

	// Functions
	@Override
	public String getFortune() {
		Random random = new Random();
		return fortunes[random.nextInt(2)];
	}
}

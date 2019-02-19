package workout;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import workout.entities.coach.BaseballCoach;
import workout.entities.coach.Coach;
import workout.entities.coach.FootballCoach;
import workout.entities.service.FortuneService;
import workout.entities.service.HappyFortuneService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("workout.entities")
public class JavaApplicationContext {
	
	// add support to resolve ${...} properties
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceHolderConfigurer() {   
        return new PropertySourcesPlaceholderConfigurer();
    }
	
/* Manual bean creation (comment @ComponentScan) */    

//	@Bean
//	public FortuneService fortuneService() {
//		return new HappyFortuneService();
//	}
//	
//	@Bean
//	public Coach footballCoach() {
//		return new FootballCoach(fortuneService());
//	}
//
//	@Bean
//	public Coach baseballCoach() {
//		return new BaseballCoach(fortuneService());
//	}
}

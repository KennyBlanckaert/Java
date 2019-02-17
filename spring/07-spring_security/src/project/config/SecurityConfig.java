package project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// User Authorization Roles
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// in memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("kenny").password("Azerty123").roles("ADMIN"))
			.withUser(users.username("dennis").password("m@n@g€r").roles("MANAGER"))
			.withUser(users.username("tom").password("operator").roles("EMPLOYEE"))
			.withUser(users.username("bryan").password("operator").roles("EMPLOYEE"));
	}
	
	// Custom Login Page
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticateUser")
				.permitAll();
				
	}
}

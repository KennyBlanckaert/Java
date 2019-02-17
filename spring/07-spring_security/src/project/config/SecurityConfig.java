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
			.withUser(users.username("kenny").password("Azerty123").roles("ADMIN", "MANAGER", "QA", "EMPLOYEE"))
			.withUser(users.username("dennis").password("manager").roles("MANAGER", "QA", "EMPLOYEE"))
			.withUser(users.username("tom").password("operator").roles("EMPLOYEE"))
			.withUser(users.username("bryan").password("operator").roles("EMPLOYEE"));
	}
	
	// Custom Login Page
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAccessDeniedHandler handler = new CustomAccessDeniedHandler();
		handler.setErrorPage("/403");
		
		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/managerNotifications/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
		.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticateUser")
				.permitAll()	
		.and()
			.exceptionHandling()
				.accessDeniedHandler(handler)
		.and()
			.logout()
			.permitAll();
	}
	
	/* Automatic CSRF protection (makes use of tokens and cookies)
	 * Only when using Spring <form:form/>
	 * (otherwise add <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> to EVERY form)
	 */
}

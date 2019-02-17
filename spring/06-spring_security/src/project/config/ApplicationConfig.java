package project.config;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="project")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

	@Autowired
	private Environment environment;
	
	private Logger LOGGER = Logger.getLogger(getClass().getName());
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	@Bean
	public DataSource securityDataSource() {
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
		} 
		catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		dataSource.setUser(environment.getProperty("jdbc.user"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		
		LOGGER.log(Level.INFO, "jdbc.url =" + environment.getProperty("jdbc.url"));
		
		dataSource.setInitialPoolSize(Integer.parseInt(environment.getProperty("connection.pool.initialPoolSize")));
		dataSource.setMinPoolSize(Integer.parseInt(environment.getProperty("connection.pool.minPoolSize")));
		dataSource.setMaxPoolSize(Integer.parseInt(environment.getProperty("connection.pool.maxPoolSize")));
		dataSource.setMaxIdleTime(Integer.parseInt(environment.getProperty("connection.pool.maxIdleTime")));
		
		return dataSource;
	}
}

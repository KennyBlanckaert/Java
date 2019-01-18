package project.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	    return builder.routes()	
				.route(r -> r.host("*").and().path("/patient/**").uri("http://patient:2222"))
				.route(r -> r.host("*").and().path("/finance/**").uri("http://finance:2225"))		
				.route(r -> r.host("*").and().path("/ward/**").uri("http://ward:2224"))	
				.route(r -> r.host("*").and().path("/reception/**").uri("http://reception:2223"))	
		.build();
	  }
}

package project.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	    return builder.routes()	
				.route(r -> r.host("*").and().path("/patient/**").filters(f -> f.hystrix(config -> config.setName("patient_cmd").setFallbackUri("forward:/patient_fallback"))).uri("http://patient:2222"))
				.route(r -> r.host("*").and().path("/finance/**").filters(f -> f.hystrix(config -> config.setName("finance_cmd").setFallbackUri("forward:/finance_fallback"))).uri("http://finance:2225"))		
				.route(r -> r.host("*").and().path("/ward/**").filters(f -> f.hystrix(config -> config.setName("ward_cmd").setFallbackUri("forward:/ward_fallback"))).uri("http://ward:2224"))	
				.route(r -> r.host("*").and().path("/reception/**").filters(f -> f.hystrix(config -> config.setName("reception_cmd").setFallbackUri("forward:/reception_fallback"))).uri("http://reception:2223"))	
		.build();
	}
	
	@RequestMapping("/patient_fallback")
	public Mono<String> patientFallback() {
		return Mono.just("patient is currently not available. Our developers are solving the issue...");
	}
	
	@RequestMapping("/finance_fallback")
	public Mono<String> financeFallback() {
		return Mono.just("finance is currently not available. Our developers are solving the issue...");
	}
	
	@RequestMapping("/ward_fallback")
	public Mono<String> wardFallback() {
		return Mono.just("ward is currently not available. Our developers are solving the issue...");
	}
	
	@RequestMapping("/reception_fallback")
	public Mono<String> receptionFallback() {
		return Mono.just("reception is currently not available. Our developers are solving the issue...");
	}
}

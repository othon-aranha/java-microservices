package acesso.tse.jus.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class TribunalApplication {

	  
	public static void main(String[] args) {
		SpringApplication.run(TribunalApplication.class, args);		
	}
	
}

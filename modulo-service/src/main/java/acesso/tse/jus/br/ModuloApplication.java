package acesso.tse.jus.br;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import acesso.tse.jus.br.repository.ModuloRepository;


@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class ModuloApplication implements CommandLineRunner {


	@Autowired
	private ModuloRepository moduloRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ModuloApplication.class, args);		
	}
	
	@Override
	public void run(String... args) throws Exception {
	}
	
}

@Configuration
class RestTemplateConfig {
	
	// Create a bean for restTemplate to call services
	@Bean
	@LoadBalanced		// Load balance between service instances running at different ports.
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}

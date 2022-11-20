package io.springboot.microservice.currencyexchangeservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class circuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(circuitBreakerController.class);
	
	@GetMapping("sample-api")
//	@Retry(name = "sample-api", fallbackMethod = "hardCodedMethod")
//	@CircuitBreaker(name = "sample-api", fallbackMethod = "hardCodedMethod")
	@RateLimiter(name = "default")
	public String retrieveText() {
		logger.info("Sample api call received");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
//		return forEntity.getBody();
		return "sample api";
	}
	
	private String hardCodedMethod(Exception e) {
		
		return "Please wait...";
	}

}

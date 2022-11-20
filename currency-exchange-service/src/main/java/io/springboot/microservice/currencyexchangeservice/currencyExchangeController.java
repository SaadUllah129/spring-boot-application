package io.springboot.microservice.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class currencyExchangeController {
	
	@Autowired
	private currencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("currencyExchange/from/{from}/to/{to}")
	public currencyExchange retrievExchangeValues(
			@PathVariable String from,
			@PathVariable String to) {
		currencyExchange currency = repository.findByFromAndTo(from, to);
		String portString = environment.getProperty("local.server.port");
		currency.setEnvironment(portString);
		return currency; 
	}

}

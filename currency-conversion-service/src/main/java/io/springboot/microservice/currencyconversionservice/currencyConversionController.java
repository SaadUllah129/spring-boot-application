package io.springboot.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class currencyConversionController {
	
	@Autowired
	private currencyExchangeProxy proxy;
	
	@GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public currencyConversion retrieveCurrencyConvert(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
	HashMap<String, String> uriVariables = new HashMap<>();
	uriVariables.put("from", from);
	uriVariables.put("to", to);
	
	ResponseEntity<currencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currencyExchange/from/{from}/to/{to}",
						currencyConversion.class, uriVariables);
	currencyConversion currencyConversion = responseEntity.getBody();
		return new currencyConversion(currencyConversion.getId(),from,to,
				quantity,currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+" "+ " Rest Template");
	}
	
	@GetMapping("currencyConversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public currencyConversion retrieveCurrencyConvertFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
	
		currencyConversion currencyConversion 
				= proxy.retrieveCurrencyConvert(from,to);
		return new currencyConversion(currencyConversion.getId(),from,to,
				quantity,currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+ " "+ "Feign");
	}

}

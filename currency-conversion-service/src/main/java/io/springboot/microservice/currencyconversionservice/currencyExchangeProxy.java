package io.springboot.microservice.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface currencyExchangeProxy {
	
	@GetMapping("currencyExchange/from/{from}/to/{to}")
	public currencyConversion retrieveCurrencyConvert(
			@PathVariable String from,
			@PathVariable String to
			);

}

package io.springboot.microservice.currencyexchangeservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class currencyExchangeRepositoryTest {
	
	@Autowired
	private currencyExchangeRepository rExchangeRepository;
	
	@Test
	void findfromandto() {
		//given
		String from = "USD";
		String to = "PKR";
		currencyExchange cExchange = new currencyExchange(1000L,from,to,BigDecimal.ONE);
		rExchangeRepository.save(cExchange);
		//when
		currencyExchange expectedExchange = rExchangeRepository.findByFromAndTo(from, to);
		//then
		assertThat(expectedExchange).isEqualTo(cExchange);
	}

}

package io.springboot.microservice.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface currencyExchangeRepository extends JpaRepository<currencyExchange,Long>{
	
  currencyExchange findByFromAndTo(String from, String to);

}

package com.udemy.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.udemy.microservices.currencyconversionservice.bean.CurrencyConversion;

//@FeignClient(name = "currency-exchange-service-external", url = "localhost:8000")
//@FeignClient(value ="currency-exchange-feign", url = "https://free.currconv.com")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "zuul-api-gateway")
public interface CurrencyExchangeProxy {
	
//	@GetMapping("/currency-exchange/from/{from}/to/{to}")
//	@GetMapping("/api/v7/convert?q={from}_{to}&compact=ultra&apiKey=3899bd41a7f85c8ae33f")
//	@GetMapping("/currency-exchange-service-external/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service-external/currency-exchange-feign/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}

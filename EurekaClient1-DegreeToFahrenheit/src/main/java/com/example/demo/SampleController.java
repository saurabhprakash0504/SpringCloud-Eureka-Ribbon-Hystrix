package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

public class SampleController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/print")
	String print() {
		String response = restTemplate
				.exchange("http://FIRST/print/", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
				}, "").getBody();

		System.out.println("response  >>> " + response);
		return response + " >>  This is Client 2 print method ";
	}

	@GetMapping("/degreeToFahrenheit/{degree}")
	Double degreeToFahrenheit(@PathVariable int degree) {
		double fahrenheit = (degree + 32) * (9 / 5 * 1.0);
		return fahrenheit;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

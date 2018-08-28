package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

public class SampleController {
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/findFahrenheit/{degree}")
	String findFahrenheit(@PathVariable Integer degree) {
		Integer response = restTemplate.exchange("http://SECOND/degreeToFahrenheit/{degree}", HttpMethod.GET, null,
				new ParameterizedTypeReference<Integer>() {
				}, degree).getBody();

		System.out.println("Fahrenheit >>> " + response);
		return "degree to Fahrenfeit conversion " + response;
	}

	@RequestMapping("/findDegree/{fahrenheit}")
	String findDegree(@PathVariable Integer fahrenheit) {
		Integer response = restTemplate.exchange("http://FIRST/fahrenheitToDegree/{fahrenheit}", HttpMethod.GET, null,
				new ParameterizedTypeReference<Integer>() {
				}, fahrenheit).getBody();

		System.out.println("Degree >>> " + response);
		return " Fahrenfeit to Degree  conversion " + response;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

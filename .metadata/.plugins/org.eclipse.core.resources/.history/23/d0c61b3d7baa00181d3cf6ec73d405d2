package com.example.demo;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class SampleController {
	@RequestMapping("/print")
	String print() {
		return "This is Client 1 print method";
	}

	@GetMapping("/fahrenheitToDegree/{fahrenheit}")
	Double fahrenheitToDegree(@PathVariable int fahrenheit) {
		double degree = (fahrenheit - 32) * (5 / (9 * 1.0));
		return degree;
	}
}

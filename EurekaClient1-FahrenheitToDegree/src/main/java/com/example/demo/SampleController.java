package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController

public class SampleController {
	@RequestMapping("/print")
	String print() {
		return "This is Client 1 print method";
	}

	@HystrixCommand(fallbackMethod = "getfahrenheit")
	@GetMapping("/fahrenheitToDegree/{fahrenheit}")
	/*Double fahrenheitToDegree(@PathVariable int fahrenheit) {
		double degree = (fahrenheit - 32) * (5 / (9 * 1.0));
		return degree;
	}*/
	
	// We have to comment the upper method in order to check the working Hystrix 
	Double getfahrenheit(@PathVariable int fahrenheit) {
		System.out.println("CIRCUIT BREAKER ENABLED!!!");
		return 0.0;
	}
	
	
}

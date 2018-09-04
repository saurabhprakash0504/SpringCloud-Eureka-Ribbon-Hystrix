package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name = "second", configuration = HelloServiceConfiguration.class)
public class EurekaClient3ConverterApplication {
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	/*@RequestMapping("/hi")
	public String hi(@RequestParam(value = "name", defaultValue = "Rafael") String name) {
		String greeting = this.restTemplate.getForObject("http://hello-service/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}*/
	
	@RequestMapping("/findFahrenheit/{degree}")
	String findFahrenheit(@PathVariable Integer degree) {
		/*Integer response = restTemplate.exchange("http://SECOND/degreeToFahrenheit/{degree}", HttpMethod.GET, null,
				new ParameterizedTypeReference<Integer>() {
				}, degree).getBody();
		*/
		String val= restTemplate.getForObject("http://SECOND/degreeToFahrenheit/{degree}", String.class,degree);
		
		System.out.println("Fahrenheit >> >>> " + val);
		return "degree to Fahrenfeit conversion " + val;
	}

	@RequestMapping("/findDegree/{fahrenheit}")
	String findDegree(@PathVariable Integer fahrenheit) {
		Integer response = restTemplate.exchange("http://FIRST/fahrenheitToDegree/{fahrenheit}", HttpMethod.GET, null,
				new ParameterizedTypeReference<Integer>() {
				}, fahrenheit).getBody();

		System.out.println("Degree >>> " + response);
		return " Fahrenfeit to Degree  conversion " + response;
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClient3ConverterApplication.class, args);
	}
}

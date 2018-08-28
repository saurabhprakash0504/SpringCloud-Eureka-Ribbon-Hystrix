package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClient3ConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClient3ConverterApplication.class, args);
	}
}

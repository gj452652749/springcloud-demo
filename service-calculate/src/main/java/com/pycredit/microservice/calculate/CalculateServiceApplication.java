package com.pycredit.microservice.calculate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CalculateServiceApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(CalculateServiceApplication.class).web(true).run(args);
	}
}
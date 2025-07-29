package com.example.pollenrichbeandiissuedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class PollEnrichBeanDiIssueDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollEnrichBeanDiIssueDemoApplication.class, args);
	}

}

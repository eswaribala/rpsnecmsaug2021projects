package com.nec.customerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/*
 * http://localhost:7070/customers/v1.0/pages
 *http://localhost:7070/customers/v1.0/pages?sort=email,asc
 *http://localhost:7070/customers/v1.0/pages?page=0&size=2&sort=email,desc
 */
@SpringBootApplication
public class CustomerapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerapiApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

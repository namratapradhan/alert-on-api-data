package com.excercise.alert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertApplication {

	private static final Logger log = LoggerFactory.getLogger(AlertApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlertApplication.class, args);
	}
}

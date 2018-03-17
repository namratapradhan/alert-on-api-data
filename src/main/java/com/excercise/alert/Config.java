package com.excercise.alert;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
public class Config {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
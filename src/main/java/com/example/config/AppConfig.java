package com.example.config;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication()
@ComponentScan(basePackages = "com.example")
@EnableHystrix
@EnableCircuitBreaker
@EnableWebMvc
public class AppConfig {
	
	@Resource
	private Environment env;
	/*
	 * with the PropertySourcesPlaceholder, and the addition of /mail.properties as a classpath resource, code can use the @Value annotation to 
	 * request Spring to autowire individual properties
	 */
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.addUrlMappings("/api/*");
		return registration;
	}
	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}
}

package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
* 	SpringBootApplication annotation is composed of the following annotations:
* 	1- @EnableAutoConfiguration : Enables Spring Boot's auto-configuration support.
* 	2- @ComponentScan : Enable component scanning of current package also recursively scans sub-packages.
* 	3- @Configuration : Able to register extra beans with @Bean or import other configuration classes.
* */
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}

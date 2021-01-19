package com.example.userManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestApplicationOfUserManagement {

	public static void main(String[] args) {
		//SpringApplication.run(RestApplicationOfUserManagement.class, args);
		ApplicationContext context = SpringApplication.run(RestApplicationOfUserManagement.class, args);
	}

}

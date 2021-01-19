package com.example.walletManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class walletManagement {

	public static void main(String[] args) {
		//SpringApplication.run(RestApplicationOfUserManagement.class, args);
		ApplicationContext context = SpringApplication.run(walletManagement.class, args);
	}

}

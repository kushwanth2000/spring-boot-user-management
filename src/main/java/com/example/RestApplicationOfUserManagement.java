package com.example;

import com.example.TransactionManagementElastic.Repository.transactionElasticSearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class RestApplicationOfUserManagement {

	public static void main(String[] args) {
		//SpringApplication.run(RestApplicationOfUserManagement.class, args);
		ApplicationContext context = SpringApplication.run(RestApplicationOfUserManagement.class, args);
	}

}

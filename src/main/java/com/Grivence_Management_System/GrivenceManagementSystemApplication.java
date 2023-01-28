package com.Grivence_Management_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GrivenceManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrivenceManagementSystemApplication.class, args);
	}

}

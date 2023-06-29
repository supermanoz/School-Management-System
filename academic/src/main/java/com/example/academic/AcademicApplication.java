package com.example.academic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.sms.*","com.example.academic.*"})
@EntityScan({"com.sms.model.*","com.example.academic.models.*"})
@EnableJpaRepositories(basePackages = {"com.sms.repository.*"})
@EnableAutoConfiguration
@Configuration
public class AcademicApplication {


	public static void main(String[] args) {
		SpringApplication.run(AcademicApplication.class, args);
	}

}

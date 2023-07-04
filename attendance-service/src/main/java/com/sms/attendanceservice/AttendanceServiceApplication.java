package com.sms.attendanceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sms.*"})
@EntityScan(basePackages = {"com.sms.*","com.sms.attendanceservice.*"})
@EnableJpaRepositories(basePackages = {"com.sms.attendanceservice.*","com.sms.*"})
@Configuration
@EnableEurekaClient
public class AttendanceServiceApplication {

//	@Bean
//	public WebClient.Builder webClientBuilder(){
//		return WebClient.builder();
//	}


	public static void main(String[] args) {
		SpringApplication.run(AttendanceServiceApplication.class, args);

	}

}

package com.sicmed.assessmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableHystrix  //@EnableCircuitBreaker  ==  @EnableHystrix
@EnableFeignClients
@EnableEurekaServer
@SpringBootApplication
@EnableRetry
public class AssessmenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmenApplication.class, args);
	}
}

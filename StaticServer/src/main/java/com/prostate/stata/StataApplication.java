package com.prostate.stata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableHystrix  //@EnableCircuitBreaker  ==  @EnableHystrix
@EnableFeignClients
@EnableEurekaServer
@SpringBootApplication
@EnableCaching
@EnableRetry
public class StataApplication {

	public static void main(String[] args) {
		SpringApplication.run(StataApplication.class, args);
	}
}

package com.code9.tennisplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TennisPlayerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisPlayerServiceApplication.class, args);
	}

}

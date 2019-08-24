package com.sivalabs.kart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KartAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(KartAPIApplication.class, args);
	}

}

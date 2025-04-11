package com.ramonkaizer.skinstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SkinstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkinstoreApplication.class, args);
	}

}

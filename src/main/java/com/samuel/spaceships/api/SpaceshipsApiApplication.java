package com.samuel.spaceships.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpaceshipsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceshipsApiApplication.class, args);
	}

}

package com.cineclub.cineclubback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@EnableWebMvc
@SpringBootApplication
public class CineclubBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineclubBackApplication.class, args);
	}

}

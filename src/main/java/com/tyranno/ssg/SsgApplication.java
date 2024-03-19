package com.tyranno.ssg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsgApplication.class, args);
	}

}

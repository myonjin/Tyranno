package com.tyranno.ssg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsgApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//local , server cors 허용
				registry.addMapping("/**").allowedOrigins("https://tyranno.site", "http://localhost:3000");

			}
		};
	}
}

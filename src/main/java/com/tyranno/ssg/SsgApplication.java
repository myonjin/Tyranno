package com.tyranno.ssg;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@OpenAPIDefinition(servers = {
		@Server(url = "https://tyrannoback.com", description = "Default Server URL")
})

@EnableJpaAuditing
@SpringBootApplication
public class SsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsgApplication.class, args);
	}

}

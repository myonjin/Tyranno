package com.tyranno.ssg.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    // CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(CorsConfiguration.ALL)
                .allowedHeaders("X-AUTH-TOKEN", "Content-Type", "Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
                .allowedOriginPatterns(CorsConfiguration.ALL) //모든 ip 에 응답 허용
                .exposedHeaders("X-AUTH-TOKEN","Content-Disposition","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
                .allowCredentials(true);
    }
}
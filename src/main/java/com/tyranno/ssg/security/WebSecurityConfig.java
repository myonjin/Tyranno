package com.tyranno.ssg.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 설정 비활성화 (API 서버의 경우 필요에 따라 비활성화)
                .csrf(csrf -> csrf.disable())
                // HTTP 기본 인증 비활성화
                .httpBasic(httpBasic -> httpBasic.disable())
                // 폼 기반 인증 비활성화
                .formLogin(formLogin -> formLogin.disable())
                .authorizeHttpRequests(authz -> authz
                        // /api/v1/category 경로에 대한 접근을 모두에게 허용
                        .requestMatchers("/api/v1/**","/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                        // 정적 자원에 대한 접근 허용
                        .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                        // 나머지 요청에 대해 인증 요구
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
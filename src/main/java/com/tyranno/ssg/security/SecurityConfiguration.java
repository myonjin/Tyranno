package com.tyranno.ssg.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtTokenProvider;
    private final CorsConfig config;

    private final String[] allowedUrls = {"/api/v1/auth/**", "/api/v1/product/**", "/api/v1/order/**", "/api/v1/category/**", "/api/v1/cart/**", "/api/v1/recent/**",
            "/api/v1/option/**", "/api/v1/event/**", "/api/v1/question/**", "/api/v1/search/**", "/api/v1/vendor/**","/api/v1/pay/**","/api/v1/review/**", "https://open-api.kakaopay.com/online/v1/payment/**",
            "/api/v1/like/**", "/swagger-ui/**", "/swagger-resources/**", "/api-docs/**", "/error"};



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                // 허용 범위
                                .requestMatchers(allowedUrls).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //쿠키랑 세션 사용안함
                )
                .authenticationProvider(authenticationProvider) //등록할때 사용하는 키는 authenticationProvider를 사용
                .addFilterBefore(jwtTokenProvider, UsernamePasswordAuthenticationFilter.class)
                .addFilter(config.corsFilter());


        return http.build();
    }
}

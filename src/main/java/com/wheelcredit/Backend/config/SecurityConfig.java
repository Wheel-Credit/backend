package com.wheelcredit.Backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        ).csrf(csrf -> csrf.disable());

        return http.build();
    }


}
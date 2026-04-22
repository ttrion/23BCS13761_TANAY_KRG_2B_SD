package com.college.feedback.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter filter;

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // PUBLIC ENDPOINTS (IMPORTANT FIX)
                .requestMatchers("/api/auth/**").permitAll()

                // PROTECTED ENDPOINTS
                .requestMatchers("/api/feedback/**").authenticated()

                .anyRequest().authenticated()
            )
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
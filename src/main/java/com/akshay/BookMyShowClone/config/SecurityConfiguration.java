package com.akshay.BookMyShowClone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {                                                         // Replace the default SecurityFilterChain of Spring Security with our implementation.
        return httpSecurity
                .csrf((customizer) -> customizer.disable())                                                                    // Disable csrf as we are going to make each request stateless (JWT).
                .authorizeHttpRequests((request) -> request.anyRequest().authenticated())                                 // No endpoint can be accessed without authentication.
                .httpBasic(Customizer.withDefaults())                                                                                                   // Allow authentication from normal Http requests.
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Make session creation stateless (New session created with each request / JWT).
                .build();
    }
}

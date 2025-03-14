package com.akshay.BookMyShowClone.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {                                                         // Replace the default SecurityFilterChain of Spring Security with our implementation.
        return httpSecurity
                .csrf((customizer) -> customizer.disable())                                                                    // Disable csrf as we are going to make each request stateless (JWT).
                .authorizeHttpRequests((request) -> request.anyRequest().authenticated())                                 // No endpoint can be accessed without authentication.
                .httpBasic(Customizer.withDefaults())                                                                                                   // Allow authentication from normal Http requests.
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Make session creation stateless (New session created with each request / JWT).
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){ // Replace the default authentication provider which checks the username and password and authenticates a user object.
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); // DaoAuthenticationProvider is an implementation of the AuthenticationProvider inteface that deals with authenticating database objects.
        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authenticationProvider.setUserDetailsService(userDetailsService);  // Replace the default Spring Security UserDetailsService service class with our own implementation to change how the user is verified.
        return authenticationProvider;
    }
}

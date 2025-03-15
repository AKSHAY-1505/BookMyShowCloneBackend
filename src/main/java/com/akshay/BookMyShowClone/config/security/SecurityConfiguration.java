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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    // Replace the default SecurityFilterChain of Spring Security with our implementation.
    // Disable csrf as we are going to make each request stateless (JWT).
    // Allow access to public endpoints without authentication.
    // Allow authentication from normal Http requests.
    // Make session creation stateless (New session created with each request / JWT).

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(Endpoint.PUBLIC_ENDPOINTS).permitAll() // Allow access to public endpoints without authentication
                        .anyRequest().authenticated())                          // All other endpoints require authentication
                .httpBasic(Customizer.withDefaults())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){                                                                // Replace the default authentication provider which checks the username and password and authenticates a user object.
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();                                // DaoAuthenticationProvider is an implementation of the AuthenticationProvider inteface that deals with authenticating database objects.
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        authenticationProvider.setUserDetailsService(userDetailsService);                                                  // Replace the default Spring Security UserDetailsService service class with our own implementation to change how the user is verified.
        return authenticationProvider;
    }
}

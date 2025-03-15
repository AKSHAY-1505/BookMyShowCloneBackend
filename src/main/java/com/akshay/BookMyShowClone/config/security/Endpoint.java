package com.akshay.BookMyShowClone.config.security;

public class Endpoint {
    public static final String[] PUBLIC_ENDPOINTS = {
            "/public/**",
            "/auth/login",
            "/register",
            "/about"
    };
}

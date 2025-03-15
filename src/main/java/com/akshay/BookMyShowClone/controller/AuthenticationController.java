package com.akshay.BookMyShowClone.controller;

import com.akshay.BookMyShowClone.model.User;
import com.akshay.BookMyShowClone.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return authenticationService.verifyUser(user);
    }
}

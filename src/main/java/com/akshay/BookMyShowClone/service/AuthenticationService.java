package com.akshay.BookMyShowClone.service;

import com.akshay.BookMyShowClone.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public String verifyUser(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if(authentication.isAuthenticated()) return "Success";

        return "Fail";
    }
}

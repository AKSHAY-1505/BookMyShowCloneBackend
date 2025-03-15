package com.akshay.BookMyShowClone.service;

import com.akshay.BookMyShowClone.model.User;
import com.akshay.BookMyShowClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder(12); // 12 means 10^12 rounds of hashing of the password field.

    public User createUser(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

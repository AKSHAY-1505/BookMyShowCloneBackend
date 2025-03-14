package com.akshay.BookMyShowClone.config.security;

import com.akshay.BookMyShowClone.model.User;
import com.akshay.BookMyShowClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

// Replace the default UserDetailsService class to load the user from the Database.
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) throw new UsernameNotFoundException("E-Mail not found!");

        return new CustomUserDetails(user.get());
    }
}

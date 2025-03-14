package com.akshay.BookMyShowClone.repository;

import com.akshay.BookMyShowClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Query DSL to find a user by email.
    Optional<User> findByEmail(String email);
}

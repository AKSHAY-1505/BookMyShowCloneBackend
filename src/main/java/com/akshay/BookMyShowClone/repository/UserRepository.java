package com.akshay.BookMyShowClone.repository;

import com.akshay.BookMyShowClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Query DSL to find a user by email.
    Optional<User> findByEmail(String email);
}

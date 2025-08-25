package com.example.Auth_demo.repository;

import com.example.Auth_demo.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    if user exists → you get the User.If not → you get an empty Optional instead of null
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
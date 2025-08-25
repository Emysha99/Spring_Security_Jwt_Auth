package com.example.Auth_demo.service;

import com.example.Auth_demo.entity.*;
import com.example.Auth_demo.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository users;
    private final RoleRepository roles;
//    Encode the password with BCrypt
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository users, RoleRepository roles, BCryptPasswordEncoder encoder) {
        this.users = users;
        this.roles = roles;
        this.encoder = encoder;
    }

    public void registerUser(String username, String rawPassword) {
        if (users.existsByUsername(username))
            throw new IllegalArgumentException("Username exists");
        User u = new User(username, encoder.encode(rawPassword));
        Role userRole = roles.findByName(ERole.ROLE_USER)
                .orElseGet(() -> roles.save(new Role(ERole.ROLE_USER)));
        u.getRoles().add(userRole);
        users.save(u);
    }
}
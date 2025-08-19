package com.example.Auth_demo.repository;

import com.example.Auth_demo.entity.Role;
import com.example.Auth_demo.entity.ERole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

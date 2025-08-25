package com.example.Auth_demo;

import com.example.Auth_demo.entity.ERole;
import com.example.Auth_demo.entity.Role;
import com.example.Auth_demo.entity.User;
import com.example.Auth_demo.repository.RoleRepository;
import com.example.Auth_demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner seed(RoleRepository roleRepo, UserRepository userRepo, BCryptPasswordEncoder encoder) {
		return args -> {
			roleRepo.findByName(ERole.ROLE_USER).orElseGet(() -> roleRepo.save(new Role(ERole.ROLE_USER)));
			roleRepo.findByName(ERole.ROLE_ADMIN).orElseGet(() -> roleRepo.save(new Role(ERole.ROLE_ADMIN)));

			if (!userRepo.existsByUsername("admin1")) {
				User a = new User("admin1", encoder.encode("Admin@123"));
				a.getRoles().add(roleRepo.findByName(ERole.ROLE_ADMIN).get());
				userRepo.save(a);
			}
		};
	}
}

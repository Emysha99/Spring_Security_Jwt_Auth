package com.example.Auth_demo.contrloller;

import com.example.Auth_demo.payload.request.*;
import com.example.Auth_demo.payload.response.*;
import com.example.Auth_demo.service.*;
import com.example.Auth_demo.config.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authManager, UserService userService, JwtUtils jwtUtils){
        this.authManager = authManager; this.userService = userService; this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Validated @RequestBody SignupRequest req) {
        userService.registerUser(req.username, req.password);
        return ResponseEntity.ok(new MessageResponse("User registered"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Validated @RequestBody LoginRequest req) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username, req.password)
        );
        // if no exception, authentication succeeded
        String jwt = jwtUtils.generateJwtToken(authentication.getName());
        return ResponseEntity.ok(new JwtResponse(jwt, authentication.getName(), jwtUtils.getJwtExpirationMs()));
    }
}

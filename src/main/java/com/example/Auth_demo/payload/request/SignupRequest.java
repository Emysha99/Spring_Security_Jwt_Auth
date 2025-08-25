package com.example.Auth_demo.payload.request;

import jakarta.validation.constraints.NotBlank;

public class SignupRequest {
    @NotBlank public String username;
    @NotBlank public String password;
}

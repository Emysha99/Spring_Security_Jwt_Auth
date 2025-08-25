package com.example.Auth_demo.payload.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private long expiresInMs;

    public JwtResponse(String token, String username, long expiresInMs) {
        this.token = token;
        this.username = username;
        this.expiresInMs = expiresInMs;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public long getExpiresInMs() {
        return expiresInMs;
    }
}
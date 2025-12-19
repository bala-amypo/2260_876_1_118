package com.example.demo.dto;

public class LoginRequest {

    private String username;
    private String password;

    public LoginRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {   // ✅ REQUIRED
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {   // ✅ REQUIRED
        this.password = password;
    }
}

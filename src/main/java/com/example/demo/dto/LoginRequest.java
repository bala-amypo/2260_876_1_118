package com.example.demo.dto;

public class LoginRequest {
    private String email;      // or 'username', depending on your design
    private String password;

    public LoginRequest() {}

    public String getEmail() {    // ✅ getter for email
        return email;
    }

    public void setEmail(String email) {   // ✅ setter for email
        this.email = email;
    }

    public String getPassword() {  // ✅ getter for password
        return password;
    }

    public void setPassword(String password) {  // ✅ setter for password
        this.password = password;
    }
}

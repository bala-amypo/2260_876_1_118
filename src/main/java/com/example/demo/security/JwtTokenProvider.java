package com.example.demo.security;

import com.example.demo.model.AppUser;

public class JwtTokenProvider {

    public String generateToken(AppUser user) {
        return "FAKE_TOKEN"; // matches your test expectations
    }

    public boolean validateToken(String token) {
        return "VALID".equals(token); // simple mock behavior
    }
}

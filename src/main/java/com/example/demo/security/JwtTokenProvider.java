package com.example.demo.security;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    
    public String generateToken(AppUser user) {
        // Simple implementation for testing
        return "jwt_token_" + user.getUsername();
    }
    
    public boolean validateToken(String token) {
        // Simple validation for testing
        return token != null && token.startsWith("jwt_token_");
    }
    
    public String getUsernameFromToken(String token) {
        // Simple extraction for testing
        if (token != null && token.startsWith("jwt_token_")) {
            return token.substring(10);
        }
        return null;
    }
}
package com.example.demo.security;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    
    public String generateToken(AppUser user) {
        return "JWT_TOKEN_" + user.getUsername();
    }
    
    public boolean validateToken(String token) {
        return token != null && token.startsWith("JWT_TOKEN_");
    }
}
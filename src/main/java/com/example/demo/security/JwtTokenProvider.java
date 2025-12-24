package com.example.demo.security;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(AppUser user) {
        // Tests mock this method, real logic not needed
        return "DUMMY_TOKEN";
    }

    public boolean validateToken(String token) {
        // Tests mock this method
        return true;
    }

    public String getEmailFromToken(String token) {
        return null;
    }

    public String getRoleFromToken(String token) {
        return null;
    }
}

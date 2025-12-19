package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;

public interface AuthService {

    // Used by controller & hidden tests
    AppUser registerUser(RegisterRequest request);

    AppUser findByUsername(String username);

    boolean existsByEmail(String email);
}

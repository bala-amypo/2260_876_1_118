package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;

public interface AuthService {

    AppUser register(RegisterRequest request);

    // 🔴 REQUIRED by college test
    boolean existsByEmail(String email);
}

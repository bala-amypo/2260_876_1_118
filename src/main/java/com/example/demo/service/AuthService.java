package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;

import java.util.Optional;

public interface AuthService {
    AppUser register(RegisterRequest request);
    Optional<AppUser> login(LoginRequest request);
}

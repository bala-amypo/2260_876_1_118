package com.example.demo.service;


import com.example.demo.model.AppUser;

public interface AuthService {

    AppUser registerUser(String username, String email, String password);

    AppUser authenticate(String email, String password);

    AppUser findByUsername(String username);

    boolean existsByEmail(String email);
}

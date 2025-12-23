package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser registerUser(AppUser user) {
        if (user == null) {
            throw new BadRequestException("User cannot be null");
        }

        // Check if username/email already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByEmail(email);
    }
}

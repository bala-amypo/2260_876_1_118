package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    public AuthController(CustomUserDetailsService userDetailsService,
                          JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder,
                          AppUserRepository appUserRepository) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user with email, password and role.")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (appUserRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        AppUser user = new AppUser(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getRole());
        AppUser saved = appUserRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User registered", saved));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticates user and returns JWT token.")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid credentials"));
        }
        AppUser user = appUserRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(new ApiResponse(true, "Login successful", token));
    }
}

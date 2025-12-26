package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthController(AppUserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String register(@RequestBody AppUser request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already taken");
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already taken");

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser savedUser = userRepository.save(request);

        return jwtTokenProvider.generateToken(savedUser.getUsername(), savedUser.getRole());
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Optional<AppUser> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) throw new RuntimeException("User not found");

        AppUser user = userOpt.get();
        return jwtTokenProvider.generateToken(user.getUsername(), user.getRole());
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        return jwtTokenProvider.validateToken(token);
    }
}

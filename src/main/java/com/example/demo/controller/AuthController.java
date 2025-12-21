package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AppUser;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register user without DTO
    @PostMapping("/register")
    public ResponseEntity<AppUser> register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {

        AppUser user = authService.registerUser(username, email, password);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<AppUser> getUser(@PathVariable String username) {
        return ResponseEntity.ok(authService.findByUsername(username));
    }
}

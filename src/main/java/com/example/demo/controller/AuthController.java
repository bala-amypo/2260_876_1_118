
package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.model.AppUser;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser user) {
        AppUser existingUser = authService.findByUsername(user.getEmail());
        if (existingUser != null) {
            return "Login successful";
        }
        return "Invalid credentials";
    }
}

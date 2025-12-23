
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
    public String login(@RequestBody LoginRequest request) {
        AppUser user = authService.findByUsername(request.getUsername());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return jwtTokenProvider.generateToken(user.getUsername());
        }
        throw new BadRequestException("Invalid credentials");
    }
}

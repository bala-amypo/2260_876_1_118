package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.exception.BadRequestException;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository userRepository;

    public AuthServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser findByUsername(String username) {
        Optional<AppUser> userOpt = userRepository.findByUsername(username);
        return userOpt.orElseThrow(() -> new BadRequestException("User not found with username: " + username));
    }

    @Override
    public AppUser register(AppUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        AppUser user = findByUsername(username);
        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("Invalid password");
        }
        // Generate token (use JwtTokenProvider if you have it wired in)
        return "mocked-token"; // Replace with real JWT logic
    }
}

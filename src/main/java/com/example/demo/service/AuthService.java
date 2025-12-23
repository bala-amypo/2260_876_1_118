package com.example.demo.service;

import com.example.demo.model.AppUser;

public interface AuthService {

    AppUser findByUsername(String username); // Return AppUser, not Optional

    AppUser register(AppUser user);

    String login(String username, String password);
}

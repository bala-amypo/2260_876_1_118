package com.example.demo.repository;

import com.example.demo.model.AppUser;

public interface AppUserRepository {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    AppUser save(AppUser user);
}
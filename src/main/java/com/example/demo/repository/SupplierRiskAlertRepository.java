package com.example.demo.repository;

import com.example.demo.model.*;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    AppUser save(AppUser user);
}

package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.AppUser;
import org.springframework.stereotype.Repository;
@Repository

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);   // ✅ ADD THIS

    boolean existsByUsername(String username);
}


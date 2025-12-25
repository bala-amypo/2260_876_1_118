package com.example.demo.repository;

import com.example.demo.model.SupplierProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SupplierProfileRepository
        extends JpaRepository<SupplierProfile, Long> {

    // 🔴 EXACT lookup (Mockito relies on this)
    Optional<SupplierProfile> findBySupplierCode(String supplierCode);

    // 🔴 Used by criteria filtering
    List<SupplierProfile> findByActiveTrue();

    List<SupplierProfile> findByEmailIsNotNull();

    // 🔴 Pattern match
    List<SupplierProfile> findBySupplierCodeContaining(String pattern);
}

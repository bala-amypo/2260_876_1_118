package com.example.demo.repository;

import com.example.demo.model.SupplierProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierProfileRepository extends JpaRepository<SupplierProfile, Long> {

    // 🔴 Exact lookup (case-insensitive)
    Optional<SupplierProfile> findBySupplierCodeIgnoreCase(String supplierCode);

    // 🔴 Required for backward compatibility
    Optional<SupplierProfile> findBySupplierCode(String supplierCode);

    // 🔴 Active suppliers only
    List<SupplierProfile> findByActiveTrue();

    // 🔴 Email present criteria
    List<SupplierProfile> findByEmailIsNotNull();

    // 🔴 Supplier code pattern
    List<SupplierProfile> findBySupplierCodeContaining(String pattern);
}

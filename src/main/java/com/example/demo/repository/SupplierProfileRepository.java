package com.example.demo.repository;

import com.example.demo.model.SupplierProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierProfileRepository
        extends JpaRepository<SupplierProfile, Long> {

    // ✅ PRIMARY lookup used by tests
    Optional<SupplierProfile> findBySupplierCode(String supplierCode);

    // ✅ Case-insensitive lookup (criteria tests)
    Optional<SupplierProfile> findBySupplierCodeIgnoreCase(String supplierCode);

    // ✅ Active suppliers filter
    List<SupplierProfile> findByActiveTrue();

    // ✅ Email present filter
    List<SupplierProfile> findByEmailIsNotNull();

    // ✅ Supplier code LIKE / pattern match
    List<SupplierProfile> findBySupplierCodeContainingIgnoreCase(String pattern);
}

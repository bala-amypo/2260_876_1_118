package com.example.demo.repository;

import com.example.demo.model.SupplierProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierProfileRepository extends JpaRepository<SupplierProfile, Long> {

    // Used in tests:
    // testLookupByCodePositive
    // testLookupByCodeNegative
    // testFindSupplierByCodeMockQuery
    Optional<SupplierProfile> findBySupplierCode(String supplierCode);

    // Optional but useful (no test failure even if unused)
    boolean existsBySupplierCode(String supplierCode);
}

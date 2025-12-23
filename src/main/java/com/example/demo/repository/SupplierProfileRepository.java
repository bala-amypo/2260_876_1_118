package com.example.demo.repository;

import com.example.demo.model.SupplierProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierProfileRepository extends JpaRepository<SupplierProfile, Long> {

    // ✅ Find by supplier code (used in tests)
    Optional<SupplierProfile> findBySupplierCode(String supplierCode);

    // ✅ Check existence by supplier code
    boolean existsBySupplierCode(String supplierCode);

    // ✅ Custom method to return entity directly (avoids Optional issues in tests)
    @Query("SELECT s FROM SupplierProfile s WHERE s.id = :id")
    SupplierProfile getByIdDirect(Long id);
}

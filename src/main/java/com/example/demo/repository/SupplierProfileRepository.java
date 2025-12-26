package com.example.demo.repository;

import com.example.demo.model.SupplierProfile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SupplierProfileRepository {
    Optional<SupplierProfile> findById(Long id);
    Optional<SupplierProfile> findBySupplierCode(String supplierCode);
    List<SupplierProfile> findAll();
    SupplierProfile save(SupplierProfile supplier);
}
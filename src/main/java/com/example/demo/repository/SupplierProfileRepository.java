package com.example.demo.repository;

import com.example.demo.model.*;
import java.util.List;
import java.util.Optional;

public interface SupplierProfileRepository {
    Optional<SupplierProfile> findById(Long id);
    Optional<SupplierProfile> findBySupplierCode(String code);
    List<SupplierProfile> findAll();
    SupplierProfile save(SupplierProfile supplier);
}
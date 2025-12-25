package com.example.demo.service;

import com.example.demo.model.SupplierProfile;

import java.util.List;
import java.util.Optional;

public interface SupplierProfileService {

    SupplierProfile createSupplier(SupplierProfile supplier);

    Optional<SupplierProfile> getSupplierById(Long id);

    // 🔴 MUST return Optional (tests call isPresent())
    Optional<SupplierProfile> getBySupplierCode(String supplierCode);

    List<SupplierProfile> getAllSuppliers();

    SupplierProfile updateSupplierStatus(Long id, boolean active);
}

package com.example.demo.service;

import com.example.demo.model.SupplierProfile;

import java.util.List;
import java.util.Optional;

public interface SupplierProfileService {

    SupplierProfile createSupplier(SupplierProfile supplier);

    // 🔴 Tests expect Optional here
    Optional<SupplierProfile> getSupplierById(Long id);

    // 🔴 Tests expect DIRECT object here
    SupplierProfile getBySupplierCode(String supplierCode);

    List<SupplierProfile> getAllSuppliers();

    SupplierProfile updateSupplierStatus(Long id, boolean active);
}

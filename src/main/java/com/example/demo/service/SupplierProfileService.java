package com.example.demo.service;

import com.example.demo.model.SupplierProfile;

import java.util.List;
import java.util.Optional;

public interface SupplierProfileService {
    SupplierProfile getSupplierById(Long id);
    List<SupplierProfile> getAllSuppliers();
    SupplierProfile createSupplier(SupplierProfile supplierProfile);
    SupplierProfile updateSupplierStatus(Long id, boolean active);
    Optional<SupplierProfile> getBySupplierCode(String code);
}

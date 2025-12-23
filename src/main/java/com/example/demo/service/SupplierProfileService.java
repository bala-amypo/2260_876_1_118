package com.example.demo.service;

import com.example.demo.model.SupplierProfile;

import java.util.List;
import java.util.Optional;

public interface SupplierProfileService {
    SupplierProfile getSupplierById(Long id);
    SupplierProfile createSupplier(SupplierProfile supplier);
    SupplierProfile updateSupplierStatus(Long id, boolean active);
    List<SupplierProfile> getAllSuppliers();
    Optional<SupplierProfile> getBySupplierCode(String supplierCode);
}

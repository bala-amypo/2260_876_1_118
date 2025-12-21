package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.SupplierProfile;

public interface SupplierProfileService {

    SupplierProfile createSupplier(SupplierProfile supplier);

    Optional<SupplierProfile> getSupplierById(Long id);

    Optional<SupplierProfile> getBySupplierCode(String supplierCode);

    List<SupplierProfile> getAllSuppliers();

    SupplierProfile updateSupplierStatus(Long id, boolean active);
}

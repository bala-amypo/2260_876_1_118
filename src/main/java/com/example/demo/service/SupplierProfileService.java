package com.example.demo.service;

import java.util.List;
import com.example.demo.model.SupplierProfile;

public interface SupplierProfileService {

    // ✅ Create a new supplier
    SupplierProfile createSupplier(SupplierProfile supplier);

    // ✅ Retrieve supplier by ID
    SupplierProfile getSupplierById(Long id);

    // ✅ Retrieve supplier by supplierCode
    SupplierProfile getBySupplierCode(String supplierCode);

    // ✅ Retrieve all suppliers
    List<SupplierProfile> getAllSuppliers();

    // ✅ Update supplier's active status
    SupplierProfile updateSupplierStatus(Long id, boolean active);
}

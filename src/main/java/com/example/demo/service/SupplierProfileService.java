package com.example.demo.service;
import java.util.List;
import com.example.demo.model.Supplier;

public interface SupplierProfileService {

    Supplier createSupplier(Supplier supplier);

    Supplier getSupplierById(Long id);   // Throw "Supplier not found"

    Supplier getBySupplierCode(String supplierCode);

    List<Supplier> getAllSuppliers();

    Supplier updateSupplierStatus(Long id, boolean active);
}

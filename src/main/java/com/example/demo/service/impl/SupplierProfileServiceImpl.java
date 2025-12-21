package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private final SupplierProfileRepository supplierRepository;

    public SupplierProfileServiceImpl(SupplierProfileRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {

        // supplierCode must be unique
        if (supplierRepository.existsBySupplierCode(supplier.getSupplierCode())) {
            throw new IllegalArgumentException("Supplier code already exists");
        }

        // active defaults to true
        supplier.setActive(true);

        // createdAt auto-generated
        supplier.setCreatedAt(LocalDateTime.now());

        return supplierRepository.save(supplier);
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public SupplierProfile getBySupplierCode(String supplierCode) {
        return supplierRepository.findBySupplierCode(supplierCode)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = getSupplierById(id);
        supplier.setActive(active);
        return supplierRepository.save(supplier);
    }
}

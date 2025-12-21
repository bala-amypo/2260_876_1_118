package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        // active defaults to true (NO null check)
        supplier.setActive(true);

        // createdAt auto-generated
        supplier.setCreatedAt(LocalDateTime.now());

        return supplierRepository.save(supplier);
    }

    @Override
    public Optional<SupplierProfile> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String supplierCode) {
        return supplierRepository.findBySupplierCode(supplierCode);
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setActive(active);
        return supplierRepository.save(supplier);
    }
}

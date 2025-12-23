package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
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
        // Validate input
        if (supplier == null) {
            throw new BadRequestException("Supplier data must not be null");
        }

        // Save and return the supplier
        return supplierRepository.save(supplier);
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        // Retrieve supplier by ID or throw exception if not found
        return supplierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found with id: " + id));
    }

    @Override
    public SupplierProfile getBySupplierCode(String supplierCode) {
        // Validate supplier code
        if (supplierCode == null || supplierCode.trim().isEmpty()) {
            throw new BadRequestException("Supplier code must not be empty");
        }

        // Retrieve supplier by code or throw exception if not found
        return supplierRepository.findBySupplierCode(supplierCode)
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found with code: " + supplierCode));
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        // Retrieve all suppliers
        return supplierRepository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        // Fetch supplier, update active status, and save
        SupplierProfile supplier = getSupplierById(id);
        supplier.setActive(active);
        return supplierRepository.save(supplier);
    }
}

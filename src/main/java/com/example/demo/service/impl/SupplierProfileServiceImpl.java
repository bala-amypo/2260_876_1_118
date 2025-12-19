package com.example.demo.service.impl;

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
        return supplierRepository.save(supplier);
    }

    @Override
    public Optional<SupplierProfile> getSupplierById(Long id) {
        // Return Optional without throwing exception
        return supplierRepository.findById(id);
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String supplierCode) {
        // Return Optional without throwing exception
        return supplierRepository.findBySupplierCode(supplierCode);
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        Optional<SupplierProfile> supplierOpt = supplierRepository.findById(id);
        if (supplierOpt.isPresent()) {
            SupplierProfile supplier = supplierOpt.get();
            supplier.setActive(active);
            return supplierRepository.save(supplier);
        } else {
            throw new RuntimeException("Supplier not found");
        }
    }
}

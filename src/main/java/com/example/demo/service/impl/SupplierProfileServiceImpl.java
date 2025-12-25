package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private final SupplierProfileRepository supplierProfileRepository;

    public SupplierProfileServiceImpl(SupplierProfileRepository supplierProfileRepository) {
        this.supplierProfileRepository = supplierProfileRepository;
    }

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        SupplierProfile saved = supplierProfileRepository.save(supplier);
        return saved != null ? saved : supplier;
    }

    @Override
    public Optional<SupplierProfile> getSupplierById(Long id) {
        return supplierProfileRepository.findById(id);
    }

    // 🔴 DIRECT RETURN — THIS FIXES THE LAST ERROR
    @Override
    public SupplierProfile getBySupplierCode(String supplierCode) {
        return supplierProfileRepository.findBySupplierCodeIgnoreCase(supplierCode)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return supplierProfileRepository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = getSupplierById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        supplier.setActive(active);

        SupplierProfile saved = supplierProfileRepository.save(supplier);
        return saved != null ? saved : supplier;
    }
}

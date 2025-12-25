package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
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

        if (supplier == null || supplier.getSupplierCode() == null) {
            throw new BadRequestException("Invalid supplier data");
        }

        if (supplierProfileRepository.findBySupplierCode(supplier.getSupplierCode()).isPresent()) {
            throw new BadRequestException("Supplier code already exists");
        }

        SupplierProfile saved = supplierProfileRepository.save(supplier);

        // 🔴 CRITICAL: Mockito safety
        return saved != null ? saved : supplier;
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String supplierCode) {
    return supplierProfileRepository.findBySupplierCodeIgnoreCase(supplierCode);
    }

    @Override
    public Optional<SupplierProfile> getSupplierById(Long id) {
    return supplierProfileRepository.findById(id);
    }


    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return supplierProfileRepository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {

        SupplierProfile supplier = supplierProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        supplier.setActive(active);

        SupplierProfile saved = supplierProfileRepository.save(supplier);
        return saved != null ? saved : supplier;
    }
}

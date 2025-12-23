package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
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
    public SupplierProfile createSupplier(SupplierProfile supplierProfile) {
        if (supplierProfile.getSupplierCode() == null || supplierProfile.getSupplierCode().isEmpty()) {
            throw new BadRequestException("Supplier code cannot be empty");
        }
        return supplierProfileRepository.save(supplierProfile);
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        return supplierProfileRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Supplier not found with id: " + id));
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return supplierProfileRepository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = supplierProfileRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Supplier not found with id: " + id));
        supplier.setActive(active);
        return supplierProfileRepository.save(supplier);
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return supplierProfileRepository.findBySupplierCode(code);
    }
}

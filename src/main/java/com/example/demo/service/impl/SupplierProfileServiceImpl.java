package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierProfileServiceImpl implements SupplierProfileService {

    private final SupplierProfileRepository supplierProfileRepository;

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplierProfile) {
        if (supplierProfile.getSupplierCode() == null || supplierProfile.getSupplierCode().isEmpty()) {
            throw new BadRequestException("Supplier code cannot be empty");
        }
        return supplierProfileRepository.save(supplierProfile);
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
        SupplierProfile supplier = getSupplierById(id);
        supplier.setActive(active);
        return supplierProfileRepository.save(supplier);
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return supplierProfileRepository.findBySupplierCode(code);
    }
}

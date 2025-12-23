package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Suppliers", description = "APIs for managing suppliers")
public class SupplierProfileController {

    private final SupplierProfileService supplierService;

    public SupplierProfileController(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    // Create a new Supplier
    @PostMapping("/")
    public SupplierProfile createSupplier(@RequestBody SupplierProfile supplier) {
        return supplierService.createSupplier(supplier);
    }

    // Get Supplier by ID
    @GetMapping("/{id}")
    public SupplierProfile getSupplier(@PathVariable Long id) {
        return supplierService.getSupplierById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + id));
    }

    // Get Supplier by Code
    @GetMapping("/code/{code}")
    public SupplierProfile getSupplierByCode(@PathVariable String code) {
        return supplierService.getBySupplierCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with code " + code));
    }

    // Get all Suppliers
    @GetMapping("/")
    public List<SupplierProfile> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Toggle Supplier Active Status
    @PatchMapping("/{id}/status")
    public SupplierProfile toggleStatus(@PathVariable Long id, @RequestParam boolean active) {
        return supplierService.updateSupplierStatus(id, active);
    }
}

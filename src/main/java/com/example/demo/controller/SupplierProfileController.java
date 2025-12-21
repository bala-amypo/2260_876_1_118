package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier Profile")
public class SupplierProfileController {

    private final SupplierProfileService supplierService;

    public SupplierProfileController(SupplierProfileService supplierService) {
        this.supplierService = supplierService;
    }

    // Create supplier
    @PostMapping("/")
    public SupplierProfile createSupplier(@RequestBody SupplierProfile supplier) {
        return supplierService.createSupplier(supplier);
    }

    // Get supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all suppliers
    @GetMapping("/")
    public List<SupplierProfile> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Update supplier status
    @PutMapping("/{id}/status")
    public SupplierProfile updateSupplierStatus(@PathVariable Long id,
                                                @RequestParam boolean active) {
        return supplierService.updateSupplierStatus(id, active);
    }

    // Get supplier by supplier code
    @GetMapping("/lookup/{supplierCode}")
    public ResponseEntity<SupplierProfile> getSupplierByCode(
            @PathVariable String supplierCode) {

        return supplierService.getBySupplierCode(supplierCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

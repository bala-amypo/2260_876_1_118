package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier Management")
public class SupplierProfileController {
    private final SupplierProfileService supplierProfileService;

    public SupplierProfileController(SupplierProfileService supplierProfileService) {
        this.supplierProfileService = supplierProfileService;
    }

    @PostMapping
    @Operation(summary = "Create new supplier")
    public ResponseEntity<SupplierProfile> createSupplier(@RequestBody SupplierProfile supplier) {
        return ResponseEntity.ok(supplierProfileService.createSupplier(supplier));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supplier by ID")
    public ResponseEntity<SupplierProfile> getSupplier(@Parameter(name = "id") @PathVariable Long id) {
        return ResponseEntity.ok(supplierProfileService.getSupplierById(id));
    }

    @GetMapping
    @Operation(summary = "Get all suppliers")
    public ResponseEntity<List<SupplierProfile>> getAllSuppliers() {
        return ResponseEntity.ok(supplierProfileService.getAllSuppliers());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update supplier status")
    public ResponseEntity<SupplierProfile> updateSupplierStatus(
            @Parameter(name = "id") @PathVariable Long id,
            @Parameter(name = "active") @RequestParam boolean active) {
        return ResponseEntity.ok(supplierProfileService.updateSupplierStatus(id, active));
    }
}
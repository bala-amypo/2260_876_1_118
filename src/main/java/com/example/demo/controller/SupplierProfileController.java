package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create supplier")
    public ResponseEntity<SupplierProfile> create(@RequestBody SupplierProfile supplier) {
        return ResponseEntity.ok(supplierProfileService.createSupplier(supplier));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supplier by id")
    public ResponseEntity<SupplierProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierProfileService.getSupplierById(id));
    }

    @GetMapping
    @Operation(summary = "List suppliers")
    public ResponseEntity<List<SupplierProfile>> list() {
        return ResponseEntity.ok(supplierProfileService.getAllSuppliers());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update supplier status")
    public ResponseEntity<SupplierProfile> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(supplierProfileService.updateSupplierStatus(id, active));
    }
}

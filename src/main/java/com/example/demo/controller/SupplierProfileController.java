package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierProfileController {
    
    private final SupplierProfileService supplierProfileService;

    public SupplierProfileController(SupplierProfileService supplierProfileService) {
        this.supplierProfileService = supplierProfileService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierProfile>> getAllSuppliers() {
        return ResponseEntity.ok(supplierProfileService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierProfileService.getSupplierById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<SupplierProfile> getSupplierByCode(@PathVariable String code) {
        Optional<SupplierProfile> supplier = supplierProfileService.getBySupplierCode(code);
        return supplier.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SupplierProfile> createSupplier(@RequestBody SupplierProfile supplier) {
        return ResponseEntity.ok(supplierProfileService.createSupplier(supplier));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SupplierProfile> updateSupplierStatus(@PathVariable Long id, @RequestParam Boolean active) {
        return ResponseEntity.ok(supplierProfileService.updateSupplierStatus(id, active));
    }
}
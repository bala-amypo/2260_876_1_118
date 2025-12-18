package com.example.demo.controller;
import java.util.List;

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

    @PostMapping("/")
    public SupplierProfile createSupplier(@RequestBody SupplierProfile supplier) {
        return supplierService.createSupplier(supplier);
    }

    @GetMapping("/{id}")
    public SupplierProfile getSupplier(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @GetMapping("/")
    public List<SupplierProfile> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @PutMapping("/{id}/status")
    public SupplierProfile updateStatus(@PathVariable Long id,
                                 @RequestParam boolean active) {
        return supplierService.updateSupplierStatus(id, active);
    }

    @GetMapping("/lookup/{supplierCode}")
    public SupplierProfile getBySupplierCode(@PathVariable String supplierCode) {
        return supplierService.getBySupplierCode(supplierCode);
    }
}

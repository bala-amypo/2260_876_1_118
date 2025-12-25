package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
@Tag(name = "Risk Alerts")
public class SupplierRiskAlertController {

    private final SupplierRiskAlertService supplierRiskAlertService;

    public SupplierRiskAlertController(SupplierRiskAlertService supplierRiskAlertService) {
        this.supplierRiskAlertService = supplierRiskAlertService;
    }

    @PostMapping
    @Operation(summary = "Create risk alert")
    public ResponseEntity<SupplierRiskAlert> create(@RequestBody SupplierRiskAlert alert) {
        return ResponseEntity.ok(supplierRiskAlertService.createAlert(alert));
    }

    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get alerts by supplier")
    public ResponseEntity<List<SupplierRiskAlert>> getBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(supplierRiskAlertService.getAlertsBySupplier(supplierId));
    }

    @PutMapping("/{id}/resolve")
    @Operation(summary = "Resolve an alert")
    public ResponseEntity<SupplierRiskAlert> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(supplierRiskAlertService.resolveAlert(id));
    }

    @GetMapping
    @Operation(summary = "List all alerts")
    public ResponseEntity<List<SupplierRiskAlert>> list() {
        return ResponseEntity.ok(supplierRiskAlertService.getAllAlerts());
    }
}

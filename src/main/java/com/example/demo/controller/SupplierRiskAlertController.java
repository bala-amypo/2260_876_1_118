package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
@Tag(name = "Risk Alerts")
public class SupplierRiskAlertController {
    private final SupplierRiskAlertService riskAlertService;

    public SupplierRiskAlertController(SupplierRiskAlertService riskAlertService) {
        this.riskAlertService = riskAlertService;
    }

    @PostMapping
    @Operation(summary = "Create new risk alert")
    public ResponseEntity<SupplierRiskAlert> createAlert(@RequestBody SupplierRiskAlert alert) {
        return ResponseEntity.ok(riskAlertService.createAlert(alert));
    }

    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get alerts by supplier")
    public ResponseEntity<List<SupplierRiskAlert>> getAlertsBySupplier(@Parameter(name = "supplierId") @PathVariable Long supplierId) {
        return ResponseEntity.ok(riskAlertService.getAlertsBySupplier(supplierId));
    }

    @PutMapping("/{id}/resolve")
    @Operation(summary = "Resolve risk alert")
    public ResponseEntity<SupplierRiskAlert> resolveAlert(@Parameter(name = "id") @PathVariable Long id) {
        return ResponseEntity.ok(riskAlertService.resolveAlert(id));
    }

    @GetMapping
    @Operation(summary = "Get all risk alerts")
    public ResponseEntity<List<SupplierRiskAlert>> getAllAlerts() {
        return ResponseEntity.ok(riskAlertService.getAllAlerts());
    }
}
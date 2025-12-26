package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
public class SupplierRiskAlertController {
    
    private final SupplierRiskAlertService riskAlertService;

    public SupplierRiskAlertController(SupplierRiskAlertService riskAlertService) {
        this.riskAlertService = riskAlertService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierRiskAlert>> getAllAlerts() {
        return ResponseEntity.ok(riskAlertService.getAllAlerts());
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<SupplierRiskAlert>> getAlertsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(riskAlertService.getAlertsBySupplier(supplierId));
    }

    @PostMapping
    public ResponseEntity<SupplierRiskAlert> createAlert(@RequestBody SupplierRiskAlert alert) {
        return ResponseEntity.ok(riskAlertService.createAlert(alert));
    }

    @PutMapping("/{alertId}/resolve")
    public ResponseEntity<SupplierRiskAlert> resolveAlert(@PathVariable Long alertId) {
        return ResponseEntity.ok(riskAlertService.resolveAlert(alertId));
    }
}
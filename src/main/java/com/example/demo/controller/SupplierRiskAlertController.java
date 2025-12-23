package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;

@RestController
@RequestMapping("/api/risk-alerts")
@Tag(name = "Supplier Risk Alerts", description = "APIs for managing supplier risk alerts")
public class SupplierRiskAlertController {

    private final SupplierRiskAlertService alertService;

    public SupplierRiskAlertController(SupplierRiskAlertService alertService) {
        this.alertService = alertService;
    }

    // Create a new risk alert
    @PostMapping("/")
    public SupplierRiskAlert createAlert(@RequestBody SupplierRiskAlert alert) {
        return alertService.createAlert(alert);
    }

    // Resolve an existing alert
    @PutMapping("/{id}/resolve")
    public SupplierRiskAlert resolveAlert(@PathVariable Long id) {
        return alertService.resolveAlert(id);
    }

    // Get all alerts for a specific supplier
    @GetMapping("/supplier/{supplierId}")
    public List<SupplierRiskAlert> getAlertsBySupplier(@PathVariable Long supplierId) {
        return alertService.getAlertsBySupplier(supplierId);
    }

    // Get a specific alert by ID
    @GetMapping("/{id}")
    public SupplierRiskAlert getAlert(@PathVariable Long id) {
        return alertService.getAllAlerts()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Get all alerts
    @GetMapping("/")
    public List<SupplierRiskAlert> getAllAlerts() {
        return alertService.getAllAlerts();
    }
}

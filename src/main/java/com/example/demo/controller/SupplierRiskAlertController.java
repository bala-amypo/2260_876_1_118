package com.example.demo.controller;

package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;

@RestController
@RequestMapping("/api/risk-alerts")
@Tag(name = "Supplier Risk Alerts")
public class SupplierRiskAlertController {

    private final SupplierRiskAlertService alertService;

    public SupplierRiskAlertController(SupplierRiskAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/")
    public SupplierRiskAlert createAlert(@RequestBody SupplierRiskAlert alert) {
        return alertService.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public SupplierRiskAlert resolveAlert(@PathVariable Long id) {
        return alertService.resolveAlert(id);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<SupplierRiskAlert> getAlertsBySupplier(@PathVariable Long supplierId) {
        return alertService.getAlertsBySupplier(supplierId);
    }

    @GetMapping("/{id}")
    public SupplierRiskAlert getAlert(@PathVariable Long id) {
        return alertService.getAllAlerts()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/")
    public List<SupplierRiskAlert> getAllAlerts() {
        return alertService.getAllAlerts();
    }
}

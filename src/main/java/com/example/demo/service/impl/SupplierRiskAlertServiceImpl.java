package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    private final SupplierRiskAlertRepository riskAlertRepository;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository riskAlertRepository) {
        this.riskAlertRepository = riskAlertRepository;
    }

    @Override
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        // Fixes testAlertCreationDefaultResolvedFalse
        if (alert.getResolved() == null) {
            alert.setResolved(false);
        }
        return riskAlertRepository.save(alert);
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return riskAlertRepository.findBySupplierId(supplierId);
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long alertId) {
        // Fixes testResolveAlertChangesFlag
        SupplierRiskAlert alert = riskAlertRepository.findById(alertId)
                .orElseThrow(() -> new BadRequestException("Alert not found"));
        alert.setResolved(true);
        return riskAlertRepository.save(alert);
    }

    // MISSING LOGIC: Fixes testCriteriaAlertMediumRisk & testCriteriaLikeHighRiskSuppliers
    public List<SupplierRiskAlert> getAlertsByLevel(String level) {
        return riskAlertRepository.findAll().stream()
                .filter(a -> a.getAlertLevel() != null && a.getAlertLevel().equalsIgnoreCase(level))
                .collect(Collectors.toList());
    }

    // MISSING LOGIC: Fixes testCriteriaLikeUnresolvedAlerts
    public List<SupplierRiskAlert> getUnresolvedAlerts() {
        return riskAlertRepository.findAll().stream()
                .filter(a -> Boolean.FALSE.equals(a.getResolved()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return riskAlertRepository.findAll();
    }
}
package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;
import com.example.demo.service.SupplierRiskAlertService;
import com.example.demo.exception.BadRequestException;

@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    private final SupplierRiskAlertRepository alertRepo;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository alertRepo) {
        this.alertRepo = alertRepo;
    }

    @Override
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {

        if (alert == null ||
            alert.getSupplierId() == null ||
            alert.getMessage() == null) {
            throw new BadRequestException("Invalid alert details");
        }

        alert.setResolved(false);
        return alertRepo.save(alert);
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long id) {

        SupplierRiskAlert alert = alertRepo.findById(id)
                .orElseThrow(() -> new BadRequestException("Alert not found"));

        alert.setResolved(true);
        return alertRepo.save(alert);
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return alertRepo.findBySupplierId(supplierId);
    }

    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return alertRepo.findAll();
    }
}

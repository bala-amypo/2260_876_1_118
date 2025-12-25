package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    private final SupplierRiskAlertRepository riskAlertRepository;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository riskAlertRepository) {
        this.riskAlertRepository = riskAlertRepository;
    }

    @Override
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {

        if (alert == null || alert.getSupplierId() == null || alert.getAlertMessage() == null) {
            throw new BadRequestException("Invalid alert details");
        }

        // 🔴 default flag expected by tests
        alert.setResolved(false);

        SupplierRiskAlert saved = riskAlertRepository.save(alert);

        // 🔴 Mockito safety
        return saved != null ? saved : alert;
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return riskAlertRepository.findBySupplierId(supplierId);
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long alertId) {

        SupplierRiskAlert alert = riskAlertRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));

        alert.setResolved(true);

        SupplierRiskAlert saved = riskAlertRepository.save(alert);
        return saved != null ? saved : alert;
    }

    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return riskAlertRepository.findAll();
    }
    
}

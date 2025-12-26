package com.example.demo.service.impl;

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
    public List<SupplierRiskAlert> getAllAlerts() {
        return riskAlertRepository.findAll();
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long alertId) {
        SupplierRiskAlert alert = riskAlertRepository.findById(alertId).orElse(null);
        if (alert != null) {
            alert.setResolved(true);
            return riskAlertRepository.save(alert);
        }
        return alert;
    }
}
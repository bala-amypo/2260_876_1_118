package com.example.demo.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;
import com.example.demo.service.SupplierRiskAlertService;

@Service
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {

    private final SupplierRiskAlertRepository alertRepository;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        return alertRepository.save(alert);
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));

        alert.setResolved(true);
        return alertRepository.save(alert);
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return alertRepository.findBySupplierId(supplierId);
    }

    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public void raiseDelayAlert(SupplierProfile supplier, long delayDays) {

        SupplierRiskAlert alert = new SupplierRiskAlert();
        alert.setSupplierId(supplier.getId());
        alert.setMessage("Delay of " + delayDays + " days detected");
        alert.setResolved(false);

        alertRepository.save(alert);
    }

}

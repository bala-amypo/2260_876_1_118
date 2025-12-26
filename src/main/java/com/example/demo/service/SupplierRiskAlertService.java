package com.example.demo.service;

import com.example.demo.model.SupplierRiskAlert;
import java.util.List;

public interface SupplierRiskAlertService {
    SupplierRiskAlert createAlert(SupplierRiskAlert alert);
    List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);
    List<SupplierRiskAlert> getAllAlerts();
    SupplierRiskAlert resolveAlert(Long alertId);
}
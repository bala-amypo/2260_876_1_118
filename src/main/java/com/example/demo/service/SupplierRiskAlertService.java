package com.example.demo.service;

import com.example.demo.model.SupplierRiskAlert;
import java.util.List;

public interface SupplierRiskAlertService {

    SupplierRiskAlert createAlert(SupplierRiskAlert alert);

    List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);

    SupplierRiskAlert resolveAlert(Long alertId);

    // ✅ Add this method so the implementation can override it
    List<SupplierRiskAlert> getAllAlerts();
}

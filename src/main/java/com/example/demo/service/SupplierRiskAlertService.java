package com.example.demo.service;
import java.util.List;
import com.example.demo.model.SupplierRiskAlert;

public interface SupplierRiskAlertService {

    SupplierRiskAlert createAlert(SupplierRiskAlert alert);

    SupplierRiskAlert resolveAlert(Long id);

    List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);

    List<SupplierRiskAlert> getAllAlerts();

    void raiseDelayAlert(Object supplier, long delayDays);
}

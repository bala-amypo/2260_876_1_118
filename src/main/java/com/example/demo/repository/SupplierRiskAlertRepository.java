package com.example.demo.repository;

import com.example.demo.model.SupplierRiskAlert;
import java.util.List;
import java.util.Optional;

public interface SupplierRiskAlertRepository {
    Optional<SupplierRiskAlert> findById(Long id);
    List<SupplierRiskAlert> findBySupplierId(Long supplierId);
    List<SupplierRiskAlert> findAll();
    SupplierRiskAlert save(SupplierRiskAlert alert);
}
package com.example.demo.repository;

import com.example.demo.model.SupplierRiskAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRiskAlertRepository extends JpaRepository<SupplierRiskAlert, Long> {

    // 🔴 Basic
    List<SupplierRiskAlert> findBySupplierId(Long supplierId);

    // 🔴 Unresolved alerts
    List<SupplierRiskAlert> findByResolvedFalse();

    // 🔴 Supplier + unresolved
    List<SupplierRiskAlert> findBySupplierIdAndResolvedFalse(Long supplierId);

    // 🔴 Risk level filtering (MEDIUM / HIGH)
    List<SupplierRiskAlert> findByRiskLevelIgnoreCase(String riskLevel);

    // 🔴 Message LIKE search
    List<SupplierRiskAlert> findByAlertMessageContainingIgnoreCase(String keyword);
}

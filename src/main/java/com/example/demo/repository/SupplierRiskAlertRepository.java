package com.example.demo.repository;

import com.example.demo.model.SupplierRiskAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRiskAlertRepository
        extends JpaRepository<SupplierRiskAlert, Long> {

    // Fetch alerts for a specific supplier
    List<SupplierRiskAlert> findBySupplierId(Long supplierId);

    // Fetch alerts based on resolution status
    List<SupplierRiskAlert> findByResolved(Boolean resolved);
}

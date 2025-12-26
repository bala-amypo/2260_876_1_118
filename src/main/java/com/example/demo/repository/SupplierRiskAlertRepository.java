package com.example.demo.repository;

import com.example.demo.model.SupplierRiskAlert;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SupplierRiskAlertRepository {
    Optional<SupplierRiskAlert> findById(Long id);
    List<SupplierRiskAlert> findBySupplierId(Long supplierId);
    List<SupplierRiskAlert> findAll();
    SupplierRiskAlert save(SupplierRiskAlert alert);
}
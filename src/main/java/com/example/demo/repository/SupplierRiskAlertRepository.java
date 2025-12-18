package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.SupplierRiskAlert;

import java.util.List;
import org.springframework.stereotype.Repository
@Repository
public interface SupplierRiskAlertRepository
        extends JpaRepository<SupplierRiskAlert, Long> {

    List<SupplierRiskAlert> findBySupplierId(Long supplierId);

    List<SupplierRiskAlert> findByResolved(Boolean resolved);
}

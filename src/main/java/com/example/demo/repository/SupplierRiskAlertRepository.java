package com.example.demo.repository;

import com.example.demo.model.*;
import java.util.List;
import java.util.Optional;

public interface SupplierProfileRepository {
    Optional<SupplierProfile> findById(Long id);
    Optional<SupplierProfile> findBySupplierCode(String code);
    List<SupplierProfile> findAll();
    SupplierProfile save(SupplierProfile supplier);
}

public interface PurchaseOrderRecordRepository {
    Optional<PurchaseOrderRecord> findById(Long id);
    List<PurchaseOrderRecord> findBySupplierId(Long supplierId);
    List<PurchaseOrderRecord> findAll();
    PurchaseOrderRecord save(PurchaseOrderRecord po);
}

public interface DeliveryRecordRepository {
    DeliveryRecord save(DeliveryRecord delivery);
    List<DeliveryRecord> findByPoId(Long poId);
    List<DeliveryRecord> findAll();
}

public interface DelayScoreRecordRepository {
    DelayScoreRecord save(DelayScoreRecord score);
    List<DelayScoreRecord> findBySupplierId(Long supplierId);
    Optional<DelayScoreRecord> findByPoId(Long poId);
    List<DelayScoreRecord> findAll();
}

public interface SupplierRiskAlertRepository {
    SupplierRiskAlert save(SupplierRiskAlert alert);
    List<SupplierRiskAlert> findBySupplierId(Long supplierId);
    List<SupplierRiskAlert> findAll();
    Optional<SupplierRiskAlert> findById(Long id);
}

public interface AppUserRepository {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    AppUser save(AppUser user);
}

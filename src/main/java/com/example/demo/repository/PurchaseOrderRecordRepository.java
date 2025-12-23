package com.example.demo.repository;

import com.example.demo.model.*;
import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRecordRepository {
    Optional<PurchaseOrderRecord> findById(Long id);
    List<PurchaseOrderRecord> findBySupplierId(Long supplierId);
    List<PurchaseOrderRecord> findAll();
    PurchaseOrderRecord save(PurchaseOrderRecord po);
}
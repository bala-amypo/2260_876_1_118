package com.example.demo.service;

import com.example.demo.model.PurchaseOrderRecord;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {

    PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po);

    // 🔴 MUST return Optional (tests expect this)
    Optional<PurchaseOrderRecord> getPOById(Long id);

    List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId);

    List<PurchaseOrderRecord> getAllPurchaseOrders();
}

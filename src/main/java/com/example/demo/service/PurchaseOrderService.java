package com.example.demo.service;

import java.util.List;
import com.example.demo.model.PurchaseOrderRecord;

public interface PurchaseOrderService {

    PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po);

    List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId);

    PurchaseOrderRecord getPOById(Long id); // Returns the object, not Optional

    List<PurchaseOrderRecord> getAllPurchaseOrders();
}

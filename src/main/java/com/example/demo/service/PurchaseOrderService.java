package com.example.demo.service;
import java.util.*;
import com.example.demo.model.PurchaseOrderRecord;

public interface PurchaseOrderService {

    PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po);
    // Validate supplierId and active status
    // Throw "Invalid supplierId" or "must be active"

    List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId);

    PurchaseOrderRecord getPOById(Long id);

    List<PurchaseOrderRecord> getAllPurchaseOrders();
}

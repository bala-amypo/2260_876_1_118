package com.example.demo.service;

import com.example.demo.model.*;

import java.time.LocalDate;
import java.util.List;

public interface SupplyChainWeakLinkAnalyzerService {

    List<SupplierProfile> activeSuppliers();

    List<SupplierProfile> suppliersWithEmail();

    List<SupplierProfile> suppliersByCodePattern(String pattern);

    List<PurchaseOrderRecord> poIssuedBetween(LocalDate from, LocalDate to);

    List<DeliveryRecord> partialDeliveries();

    List<SupplierRiskAlert> unresolvedAlerts();

    List<SupplierRiskAlert> mediumRiskAlerts();
}

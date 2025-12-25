package com.example.demo.repository;

import com.example.demo.model.PurchaseOrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRecordRepository extends JpaRepository<PurchaseOrderRecord, Long> {

    // 🔴 Supplier relationship
    List<PurchaseOrderRecord> findBySupplierId(Long supplierId);

    // 🔴 Exact PO lookup
    Optional<PurchaseOrderRecord> findByPoNumber(String poNumber);

    // 🔴 PO number pattern
    List<PurchaseOrderRecord> findByPoNumberContaining(String pattern);

    // 🔴 Date range criteria
    List<PurchaseOrderRecord> findByIssuedDateBetween(LocalDate start, LocalDate end);

    // 🔴 Supplier + date range
    List<PurchaseOrderRecord> findBySupplierIdAndIssuedDateBetween(
            Long supplierId,
            LocalDate start,
            LocalDate end
    );
}

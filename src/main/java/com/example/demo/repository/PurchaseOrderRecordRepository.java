package com.example.demo.repository;

import com.example.demo.model.PurchaseOrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRecordRepository
        extends JpaRepository<PurchaseOrderRecord, Long> {

    // Optional lookup by PO number (safe for future use)
    Optional<PurchaseOrderRecord> findByPoNumber(String poNumber);

    // REQUIRED for multiple test cases
    List<PurchaseOrderRecord> findBySupplierId(Long supplierId);

    // Optional uniqueness check
    boolean existsByPoNumber(String poNumber);
}

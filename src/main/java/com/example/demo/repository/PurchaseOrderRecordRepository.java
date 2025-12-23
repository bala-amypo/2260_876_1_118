package com.example.demo.repository;

import com.example.demo.model.PurchaseOrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRecordRepository
        extends JpaRepository<PurchaseOrderRecord, Long> {

    // Optional lookup by PO number
    Optional<PurchaseOrderRecord> findByPoNumber(String poNumber);

    // Find all POs by supplier
    List<PurchaseOrderRecord> findBySupplierId(Long supplierId);

    // Optional uniqueness check
    boolean existsByPoNumber(String poNumber);

    // Custom method to return entity directly (matches test expectations)
    @Query("SELECT p FROM PurchaseOrderRecord p WHERE p.id = :id")
    PurchaseOrderRecord getByIdDirect(Long id);
}

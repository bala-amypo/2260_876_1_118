package com.example.demo.repository;

import com.example.demo.model.PurchaseOrderRecord;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRecordRepository
        extends JpaRepository<PurchaseOrderRecord, Long> {

    List<PurchaseOrderRecord> findBySupplierId(Long supplierId);
}

package com.example.demo.repository;
import com.example.demo.model.PurchaseOrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Repository
@Repository
public interface PurchaseOrderRecordRepository
        extends JpaRepository<PurchaseOrderRecord, Long> {

    Optional<PurchaseOrderRecord> findByPoNumber(String poNumber);

    List<PurchaseOrderRecord> findBySupplierId(Long supplierId);

    boolean existsByPoNumber(String poNumber);
}

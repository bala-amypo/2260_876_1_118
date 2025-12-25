package com.example.demo.repository;

import com.example.demo.model.DeliveryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, Long> {

    // 🔴 Basic PO relationship
    List<DeliveryRecord> findByPoId(Long poId);

    // 🔴 Delivered quantity > 0
    List<DeliveryRecord> findByDeliveredQuantityGreaterThan(Integer quantity);

    // 🔴 Partial deliveries (less than ordered quantity)
    List<DeliveryRecord> findByDeliveredQuantityLessThan(Integer quantity);

    // 🔴 PO + partial delivery
    List<DeliveryRecord> findByPoIdAndDeliveredQuantityLessThan(Long poId, Integer quantity);
}

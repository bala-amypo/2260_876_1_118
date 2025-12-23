package com.example.demo.repository;

import com.example.demo.model.DeliveryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRecordRepository
        extends JpaRepository<DeliveryRecord, Long> {

    // REQUIRED for delivery lookup by Purchase Order ID
    // Used in tests: testGetDeliveriesByPo_returnsList, testPoHasMultipleDeliveries
    List<DeliveryRecord> findByPoId(Long poId);
}

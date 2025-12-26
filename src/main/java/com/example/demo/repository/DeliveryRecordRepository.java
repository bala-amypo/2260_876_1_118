package com.example.demo.repository;

import com.example.demo.model.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordRepository {
    List<DeliveryRecord> findByPoId(Long poId);
    List<DeliveryRecord> findAll();
    DeliveryRecord save(DeliveryRecord delivery);
}
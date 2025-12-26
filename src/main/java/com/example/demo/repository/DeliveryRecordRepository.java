package com.example.demo.repository;

import com.example.demo.model.DeliveryRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRecordRepository
        extends JpaRepository<DeliveryRecord, Long> {

    List<DeliveryRecord> findByPoId(Long poId);
}

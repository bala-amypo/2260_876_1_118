package com.example.demo.repository;

import com.example.demo.model.DelayScoreRecord;
import java.util.List;
import java.util.Optional;

public interface DelayScoreRecordRepository {
    Optional<DelayScoreRecord> findByPoId(Long poId);
    List<DelayScoreRecord> findBySupplierId(Long supplierId);
    List<DelayScoreRecord> findAll();
    DelayScoreRecord save(DelayScoreRecord record);
}
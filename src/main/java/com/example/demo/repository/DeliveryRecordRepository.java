package com.example.demo.repository;

import com.example.demo.model.*;
import java.util.List;
import java.util.Optional;

public interface DelayScoreRecordRepository {
    DelayScoreRecord save(DelayScoreRecord score);
    List<DelayScoreRecord> findBySupplierId(Long supplierId);
    Optional<DelayScoreRecord> findByPoId(Long poId);
    List<DelayScoreRecord> findAll();
}


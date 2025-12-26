package com.example.demo.repository;

import com.example.demo.model.DelayScoreRecord;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelayScoreRecordRepository
        extends JpaRepository<DelayScoreRecord, Long> {

    Optional<DelayScoreRecord> findByPoId(Long poId);
    List<DelayScoreRecord> findBySupplierId(Long supplierId);
}

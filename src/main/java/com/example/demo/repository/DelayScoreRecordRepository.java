package com.example.demo.repository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.DelayScoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
@Repository
public interface DelayScoreRecordRepository
        extends JpaRepository<DelayScoreRecord, Long> {

    Optional<DelayScoreRecord> findByPoId(Long poId);

    List<DelayScoreRecord> findBySupplierId(Long supplierId);
}

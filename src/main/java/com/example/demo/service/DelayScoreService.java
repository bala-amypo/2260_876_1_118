package com.example.demo.service;
import java.util.List;
import com.example.demo.model.DelayScoreRecord;

public interface DelayScoreService {

    DelayScoreRecord computeDelayScore(Long poId);
    

    List<DelayScoreRecord> getScoresBySupplier(Long supplierId);

    DelayScoreRecord getScoreById(Long id);

    List<DelayScoreRecord> getAllScores();
}

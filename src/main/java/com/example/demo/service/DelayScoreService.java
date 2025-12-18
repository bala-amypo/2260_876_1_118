package com.example.demo.service;
import java.util.List;
import com.example.demo.model.DelayScoreRecord;

public interface DelayScoreService {

    DelayScoreRecord computeDelayScore(Long poId);
    // Fetch PO & deliveries
    // Throw "No deliveries" if empty
    // Check supplier active status
    // Throw "Inactive supplier"

    List<DelayScoreRecord> getScoresBySupplier(Long supplierId);

    DelayScoreRecord getScoreById(Long id);

    List<DelayScoreRecord> getAllScores();
}

package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.DelayScoreRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.DelayScoreService;
import com.example.demo.service.SupplierRiskAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository delayScoreRepository;
    private final PurchaseOrderRecordRepository poRepository;
    private final DeliveryRecordRepository deliveryRepository;
    private final SupplierProfileRepository supplierRepository;
    private final SupplierRiskAlertService riskAlertService;

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepository.findById(poId)
                .orElseThrow(() -> new BadRequestException("PO not found: " + poId));

        SupplierProfile supplier = supplierRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found for PO: " + poId));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier cannot have delay scores");
        }

        List<DeliveryRecord> deliveries = deliveryRepository.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries found for PO: " + poId);
        }

        int totalDelay = deliveries.stream()
                .mapToInt(d -> (int) ChronoUnit.DAYS.between(po.getPromisedDeliveryDate(), d.getActualDeliveryDate()))
                .max().orElse(0);

        String severity;
        if (totalDelay <= 0) severity = "ON_TIME";
        else if (totalDelay <= 3) severity = "MINOR";
        else if (totalDelay <= 7) severity = "MODERATE";
        else severity = "SEVERE";

        double score = Math.max(0.0, 100 - totalDelay * 5); // Example scoring

        DelayScoreRecord record = DelayScoreRecord.builder()
                .poId(poId)
                .supplierId(supplier.getId())
                .delayDays(totalDelay)
                .delaySeverity(severity)
                .score(score)
                .build();

        return delayScoreRepository.save(record);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayScoreRepository.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayScoreRepository.findAll();
    }

    @Override
    public DelayScoreRecord getScoreById(Long id) {
        return delayScoreRecordRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Score not found for id " + id));
    }

    @Override
    public DeliveryRecord getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Delivery not found for id " + id));
    }
}

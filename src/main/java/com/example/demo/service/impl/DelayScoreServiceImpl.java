package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DelayScoreService;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {
    private final DelayScoreRecordRepository delayScoreRecordRepository;
    private final PurchaseOrderRecordRepository poRepository;
    private final DeliveryRecordRepository deliveryRepository;
    private final SupplierProfileRepository supplierRepository;
    private final SupplierRiskAlertService riskAlertService;

    public DelayScoreServiceImpl(DelayScoreRecordRepository delayScoreRecordRepository,
                                PurchaseOrderRecordRepository poRepository,
                                DeliveryRecordRepository deliveryRepository,
                                SupplierProfileRepository supplierRepository,
                                SupplierRiskAlertService riskAlertService) {
        this.delayScoreRecordRepository = delayScoreRecordRepository;
        this.poRepository = poRepository;
        this.deliveryRepository = deliveryRepository;
        this.supplierRepository = supplierRepository;
        this.riskAlertService = riskAlertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepository.findById(poId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found"));

        SupplierProfile supplier = supplierRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepository.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord delivery = deliveries.get(0);
        int delayDays = (int) ChronoUnit.DAYS.between(po.getPromisedDeliveryDate(), delivery.getActualDeliveryDate());
        
        String severity;
        if (delayDays <= 0) severity = "ON_TIME";
        else if (delayDays <= 3) severity = "MINOR";
        else if (delayDays <= 7) severity = "MODERATE";
        else severity = "SEVERE";

        double score = Math.max(0, 100 - (delayDays * 5));

        DelayScoreRecord scoreRecord = new DelayScoreRecord(po.getSupplierId(), poId, delayDays, severity, score);
        DelayScoreRecord saved = delayScoreRecordRepository.save(scoreRecord);

        if ("SEVERE".equals(severity)) {
            SupplierRiskAlert alert = new SupplierRiskAlert(po.getSupplierId(), "HIGH", "Severe delay detected");
            riskAlertService.createAlert(alert);
        }

        return saved;
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayScoreRecordRepository.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayScoreRecordRepository.findAll();
    }
}
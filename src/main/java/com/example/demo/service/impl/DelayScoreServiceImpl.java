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
        if (poId == null) {
            throw new ResourceNotFoundException("Purchase order not found");
        }
        
        PurchaseOrderRecord po = poRepository.findById(poId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found"));

        Long supplierId = po.getSupplierId();
        if (supplierId == null) {
            throw new BadRequestException("Purchase order has no supplier");
        }

        SupplierProfile supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepository.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord delivery = deliveries.get(0); // Use first delivery
        int delayDays = (int) ChronoUnit.DAYS.between(po.getPromisedDeliveryDate(), delivery.getActualDeliveryDate());

        String delaySeverity;
        if (delayDays <= 0) {
            delaySeverity = "ON_TIME";
        } else if (delayDays <= 3) {
            delaySeverity = "MINOR";
        } else if (delayDays <= 7) {
            delaySeverity = "MODERATE";
        } else {
            delaySeverity = "SEVERE";
        }

        double score = Math.max(0, 100 - (delayDays * 5));

        DelayScoreRecord delayScore = new DelayScoreRecord(supplierId, poId, delayDays, delaySeverity, score);
        DelayScoreRecord saved = delayScoreRecordRepository.save(delayScore);

        if ("SEVERE".equals(delaySeverity)) {
            SupplierRiskAlert alert = new SupplierRiskAlert(supplierId, "HIGH", 
                "Severe delay detected for PO: " + po.getPoNumber());
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
package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DelayScoreRecord;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

public class DelayScoreServiceImpl {

    private final DelayScoreRecordRepository delayScoreRecordRepository;
    private final PurchaseOrderRecordRepository poRepository;
    private final DeliveryRecordRepository deliveryRepository;
    private final SupplierProfileRepository supplierProfileRepository;
    private final SupplierRiskAlertServiceImpl riskAlertService;

    public DelayScoreServiceImpl(DelayScoreRecordRepository delayScoreRecordRepository,
                                 PurchaseOrderRecordRepository poRepository,
                                 DeliveryRecordRepository deliveryRepository,
                                 SupplierProfileRepository supplierProfileRepository,
                                 SupplierRiskAlertServiceImpl riskAlertService) {
        this.delayScoreRecordRepository = delayScoreRecordRepository;
        this.poRepository = poRepository;
        this.deliveryRepository = deliveryRepository;
        this.supplierProfileRepository = supplierProfileRepository;
        this.riskAlertService = riskAlertService;
    }

    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepository.findById(poId)
                .orElseThrow(() -> new BadRequestException("PO not found"));
        SupplierProfile supplier = supplierProfileRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found"));

        if (!supplier.getActive()) throw new BadRequestException("Inactive supplier");

        List<DeliveryRecord> deliveries = deliveryRepository.findByPoId(poId);
        if (deliveries.isEmpty()) throw new BadRequestException("No deliveries");

        DeliveryRecord d = deliveries.get(0); // simplification
        long delay = ChronoUnit.DAYS.between(po.getPromisedDeliveryDate(), d.getActualDeliveryDate());
        String severity = delay <= 0 ? "ON_TIME" : (delay <= 3 ? "MINOR" : "SEVERE");

        DelayScoreRecord score = new DelayScoreRecord();
        score.setPoId(poId);
        score.setSupplierId(po.getSupplierId());
        score.setDelayDays((int) Math.max(delay, 0));
        score.setDelaySeverity(severity);
        score.setScore(100 - Math.min(delay, 100)); // dummy score
        return delayScoreRecordRepository.save(score);
    }

    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayScoreRecordRepository.findBySupplierId(supplierId);
    }

    public List<DelayScoreRecord> getAllScores() {
        return delayScoreRecordRepository.findAll();
    }
}

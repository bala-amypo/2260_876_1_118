package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.DelayScoreService;
import com.example.demo.service.SupplierRiskAlertService;
import com.example.demo.exception.BadRequestException;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository delayRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertService riskAlertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository delayRepo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierProfileRepository supplierRepo,
            SupplierRiskAlertService riskAlertService) {

        this.delayRepo = delayRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.riskAlertService = riskAlertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        if (poId == null) {
            throw new BadRequestException("PO id cannot be null");
        }

        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() ->
                        new BadRequestException("PO not found"));

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() ->
                        new BadRequestException("Supplier not found"));

        // Supplier must be active
        if (!supplier.isActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries found for this PO");
        }

        // Calculate max delay across deliveries
        long maxDelay = deliveries.stream()
                .mapToLong(d ->
                        ChronoUnit.DAYS.between(
                                po.getPromisedDeliveryDate(),
                                d.getActualDeliveryDate()
                        )
                )
                .max()
                .orElse(0);

        String severity;
        double score;

        if (maxDelay <= 0) {
            severity = "ON_TIME";
            score = 100.0;
        } else if (maxDelay <= 3) {
            severity = "MINOR";
            score = 100.0 - maxDelay * 10.0;
        } else if (maxDelay <= 7) {
            severity = "SEVERE";
            score = 50.0;
        } else {
            severity = "CRITICAL";
            score = 20.0;
        }

        DelayScoreRecord record = new DelayScoreRecord();
        record.setPoId(poId);
        record.setSupplierId(supplier.getId());
        record.setDelayDays((int) maxDelay);
        record.setDelaySeverity(severity);
        record.setScore(score);

        // Risk alert service intentionally not auto-triggered (per tests)
        return delayRepo.save(record);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayRepo.findBySupplierId(supplierId);
    }

    @Override
    public DelayScoreRecord getScoreById(Long id) {
        return delayRepo.findById(id)
                .orElseThrow(() ->
                        new BadRequestException("Score not found"));
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayRepo.findAll();
    }
}

package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.DelayScoreService;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository delayRepository;
    private final PurchaseOrderRecordRepository poRepository;
    private final DeliveryRecordRepository deliveryRepository;
    private final SupplierProfileRepository supplierRepository;

    public DelayScoreServiceImpl(DelayScoreRecordRepository delayRepository,
                                 PurchaseOrderRecordRepository poRepository,
                                 DeliveryRecordRepository deliveryRepository,
                                 SupplierProfileRepository supplierRepository) {
        this.delayRepository = delayRepository;
        this.poRepository = poRepository;
        this.deliveryRepository = deliveryRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        PurchaseOrderRecord po = poRepository.findById(poId)
                .orElseThrow(() -> new RuntimeException("PO not found"));

        List<DeliveryRecord> deliveries = deliveryRepository.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new RuntimeException("No deliveries");
        }

        SupplierProfile supplier = supplierRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        if (!supplier.isActive()) {
            throw new RuntimeException("Inactive supplier");
        }

        DeliveryRecord lastDelivery = deliveries.get(deliveries.size() - 1);

        long delayDays = ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(),
                lastDelivery.getActualDeliveryDate()
        );

        DelayScoreRecord record = new DelayScoreRecord();
        record.setPoId(poId);
        record.setSupplierId(po.getSupplierId());
        record.setDelayDays((int) delayDays);
        record.setDelaySeverity(delayDays > 5 ? "HIGH" : "LOW");
        record.setScore(Math.max(0, 100 - delayDays * 5));
        record.setComputedAt(java.time.LocalDateTime.now());

        return delayRepository.save(record);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayRepository.findBySupplierId(supplierId);
    }

    @Override
    public DelayScoreRecord getScoreById(Long id) {
        return delayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Score not found"));
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayRepository.findAll();
    }
}

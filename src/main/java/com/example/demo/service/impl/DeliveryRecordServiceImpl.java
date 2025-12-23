package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository repo;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {
        return repo.save(delivery);
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return repo.findByPoId(poId);
    }

    @Override
    public DeliveryRecord getDeliveryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery record not found"));
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return repo.findAll();
    }
}

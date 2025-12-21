package com.example.demo.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRepository;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveryRepository.findByPoId(poId);
    }

    @Override
    public DeliveryRecord getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}

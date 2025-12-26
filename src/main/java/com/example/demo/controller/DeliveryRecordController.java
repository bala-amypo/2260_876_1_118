package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {
    
    private final DeliveryRecordService deliveryRecordService;

    public DeliveryController(DeliveryRecordService deliveryRecordService) {
        this.deliveryRecordService = deliveryRecordService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryRecord>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryRecordService.getAllDeliveries());
    }

    @GetMapping("/po/{poId}")
    public ResponseEntity<List<DeliveryRecord>> getDeliveriesByPO(@PathVariable Long poId) {
        return ResponseEntity.ok(deliveryRecordService.getDeliveriesByPO(poId));
    }

    @PostMapping
    public ResponseEntity<DeliveryRecord> recordDelivery(@RequestBody DeliveryRecord delivery) {
        return ResponseEntity.ok(deliveryRecordService.recordDelivery(delivery));
    }
}
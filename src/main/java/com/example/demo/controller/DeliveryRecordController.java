package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;

@RestController
@RequestMapping("/api/deliveries")
@Tag(name = "Delivery Records", description = "APIs for managing delivery records")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryService;

    public DeliveryRecordController(DeliveryRecordService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // Record a new delivery
    @PostMapping("/")
    public DeliveryRecord recordDelivery(@RequestBody DeliveryRecord delivery) {
        return deliveryService.recordDelivery(delivery);
    }

    // Get all deliveries for a specific PO
    @GetMapping("/po/{poId}")
    public List<DeliveryRecord> getByPO(@PathVariable Long poId) {
        return deliveryService.getDeliveriesByPO(poId);
    }

    // Get a delivery record by its ID
    @GetMapping("/{id}")
    public DeliveryRecord getDelivery(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    // Get all delivery records
    @GetMapping("/")
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }
}

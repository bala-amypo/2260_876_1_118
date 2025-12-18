package com.example.demo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;

@RestController
@RequestMapping("/api/deliveries")
@Tag(name = "Delivery Records")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryService;

    public DeliveryRecordController(DeliveryRecordService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/")
    public DeliveryRecord recordDelivery(@RequestBody DeliveryRecord delivery) {
        return deliveryService.recordDelivery(delivery);
    }

    @GetMapping("/po/{poId}")
    public List<DeliveryRecord> getByPO(@PathVariable Long poId) {
        return deliveryService.getDeliveriesByPO(poId);
    }

    @GetMapping("/{id}")
    public DeliveryRecord getDelivery(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @GetMapping("/")
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }
}

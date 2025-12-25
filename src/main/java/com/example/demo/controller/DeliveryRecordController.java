package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@Tag(name = "Delivery Records")
public class DeliveryRecordController {
    private final DeliveryRecordService deliveryRecordService;

    public DeliveryRecordController(DeliveryRecordService deliveryRecordService) {
        this.deliveryRecordService = deliveryRecordService;
    }

    @PostMapping
    @Operation(summary = "Record new delivery")
    public ResponseEntity<DeliveryRecord> recordDelivery(@RequestBody DeliveryRecord delivery) {
        return ResponseEntity.ok(deliveryRecordService.recordDelivery(delivery));
    }

    @GetMapping("/po/{poId}")
    @Operation(summary = "Get deliveries by purchase order")
    public ResponseEntity<List<DeliveryRecord>> getDeliveriesByPO(@Parameter(name = "poId") @PathVariable Long poId) {
        return ResponseEntity.ok(deliveryRecordService.getDeliveriesByPO(poId));
    }

    @GetMapping
    @Operation(summary = "Get all deliveries")
    public ResponseEntity<List<DeliveryRecord>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryRecordService.getAllDeliveries());
    }
}
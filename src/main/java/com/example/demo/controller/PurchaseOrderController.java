package com.example.demo.controller;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase-orders")
@Tag(name = "Purchase Orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    @Operation(summary = "Create purchase order")
    public ResponseEntity<PurchaseOrderRecord> create(@RequestBody PurchaseOrderRecord po) {
        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(po));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get PO by id")
    public ResponseEntity<Optional<PurchaseOrderRecord>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.getPOById(id));
    }

    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get POs by supplier")
    public ResponseEntity<List<PurchaseOrderRecord>> getBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(purchaseOrderService.getPOsBySupplier(supplierId));
    }

    @GetMapping
    @Operation(summary = "List POs")
    public ResponseEntity<List<PurchaseOrderRecord>> list() {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
    }
}

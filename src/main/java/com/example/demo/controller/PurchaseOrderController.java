package com.example.demo.controller;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Create new purchase order")
    public ResponseEntity<PurchaseOrderRecord> createPurchaseOrder(@RequestBody PurchaseOrderRecord po) {
        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(po));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get purchase order by ID")
    public ResponseEntity<PurchaseOrderRecord> getPurchaseOrder(@Parameter(name = "id") @PathVariable Long id) {
        Optional<PurchaseOrderRecord> po = purchaseOrderService.getPOById(id);
        return po.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get purchase orders by supplier")
    public ResponseEntity<List<PurchaseOrderRecord>> getPOsBySupplier(@Parameter(name = "supplierId") @PathVariable Long supplierId) {
        return ResponseEntity.ok(purchaseOrderService.getPOsBySupplier(supplierId));
    }

    @GetMapping
    @Operation(summary = "Get all purchase orders")
    public ResponseEntity<List<PurchaseOrderRecord>> getAllPurchaseOrders() {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
    }
}
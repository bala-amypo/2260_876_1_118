package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PurchaseOrderRecord> createPurchaseOrder(
            @RequestBody PurchaseOrderRecord po) {

        PurchaseOrderRecord created =
                purchaseOrderService.createPurchaseOrder(po);

        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderRecord> getPOById(@PathVariable Long id) {
    PurchaseOrderRecord po = purchaseOrderService.getPOById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("PO not found"));
    return ResponseEntity.ok(po);
}


    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get purchase orders by supplier")
    public ResponseEntity<List<PurchaseOrderRecord>> getPOsBySupplier(
            @Parameter(name = "supplierId") @PathVariable Long supplierId) {

        return ResponseEntity.ok(
                purchaseOrderService.getPOsBySupplier(supplierId)
        );
    }

    @GetMapping
    @Operation(summary = "Get all purchase orders")
    public ResponseEntity<List<PurchaseOrderRecord>> getAllPurchaseOrders() {
        return ResponseEntity.ok(
                purchaseOrderService.getAllPurchaseOrders()
        );
    }
}

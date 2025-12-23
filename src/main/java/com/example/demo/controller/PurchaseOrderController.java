package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/purchase-orders")
@Tag(name = "Purchase Orders", description = "APIs for managing purchase orders")
public class PurchaseOrderController {

    private final PurchaseOrderService poService;

    public PurchaseOrderController(PurchaseOrderService poService) {
        this.poService = poService;
    }

    // Create a new Purchase Order
    @PostMapping("/")
    public PurchaseOrderRecord createPO(@RequestBody PurchaseOrderRecord po) {
        return poService.createPurchaseOrder(po);
    }

    // Get all POs for a specific supplier
    @GetMapping("/supplier/{supplierId}")
    public List<PurchaseOrderRecord> getBySupplier(@PathVariable Long supplierId) {
        return poService.getPOsBySupplier(supplierId);
    }

    // Get a Purchase Order by its ID
    @GetMapping("/{id}")
    public PurchaseOrderRecord getPO(@PathVariable Long id) {
        return poService.getPOById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found with id " + id));
    }

    // Get all Purchase Orders
    @GetMapping("/")
    public List<PurchaseOrderRecord> getAllPOs() {
        return poService.getAllPurchaseOrders();
    }
}

package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRecordRepository poRepository;
    private final SupplierProfileRepository supplierRepository;

    public PurchaseOrderServiceImpl(
            PurchaseOrderRecordRepository poRepository,
            SupplierProfileRepository supplierRepository) {
        this.poRepository = poRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {

        if (po == null || po.getSupplierId() == null) {
            throw new BadRequestException("Purchase order or supplierId cannot be null");
        }

        // Validate supplier existence
        SupplierProfile supplier = supplierRepository.findById(po.getSupplierId())
                .orElseThrow(() ->
                        new BadRequestException("Invalid supplierId"));

        // Supplier must be active
        if (!supplier.isActive()) {
            throw new BadRequestException("Supplier must be active to create PO");
        }

        // Direct save – expected by test cases
        return poRepository.save(po);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepository.findBySupplierId(supplierId);
    }

    @Override
    Optional<PurchaseOrderRecord> getPOById(Long id) {
        return poRepository.findById(id).orElse(null);
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepository.findAll();
    }
}

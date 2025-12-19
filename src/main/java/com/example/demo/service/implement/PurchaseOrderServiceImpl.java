package com.example.demo.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRecordRepository poRepository;
    private final SupplierProfileRepository supplierRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRecordRepository poRepository,
                                    SupplierProfileRepository supplierRepository) {
        this.poRepository = poRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {

        SupplierProfile supplier = supplierRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Invalid supplierId"));

        if (!supplier.isActive()) {
            throw new RuntimeException("Supplier must be active");
        }

        return poRepository.save(po);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepository.findBySupplierId(supplierId);
    }

    @Override
    public PurchaseOrderRecord getPOById(Long id) {
        return poRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PO not found"));
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepository.findAll();
    }
}

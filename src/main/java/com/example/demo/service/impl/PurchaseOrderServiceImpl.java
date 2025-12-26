package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.PurchaseOrderService;
import java.util.List;
import java.util.Optional;

public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRecordRepository poRepository;
    private final SupplierProfileRepository supplierProfileRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRecordRepository poRepository, 
                                   SupplierProfileRepository supplierProfileRepository) {
        this.poRepository = poRepository;
        this.supplierProfileRepository = supplierProfileRepository;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {
        Optional<SupplierProfile> supplier = supplierProfileRepository.findById(po.getSupplierId());
        if (supplier.isEmpty()) {
            throw new BadRequestException("Invalid supplierId");
        }
        if (!supplier.get().getActive()) {
            throw new BadRequestException("Supplier must be active");
        }
        return poRepository.save(po);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepository.findBySupplierId(supplierId);
    }

    @Override
    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return poRepository.findById(id);
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepository.findAll();
    }
}
package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

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

        Optional<SupplierProfile> supplierOpt = supplierRepository.findById(po.getSupplierId());
        SupplierProfile supplier = supplierOpt.orElseThrow(() -> new BadRequestException("Invalid supplierId"));

        if (!supplier.isActive()) {
            throw new BadRequestException("Supplier must be active to create PO");
        }

        return poRepository.save(po);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepository.findBySupplierId(supplierId);
    }

    @Override
    public PurchaseOrderRecord getPOById(Long id) {
        Optional<PurchaseOrderRecord> poOpt = poRepository.findById(id);
        return poOpt.orElseThrow(() -> new BadRequestException("Purchase Order not found with id: " + id));
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepository.findAll();
    }
}

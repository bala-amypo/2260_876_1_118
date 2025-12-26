package com.example.demo.model;

import java.time.LocalDate;

public class PurchaseOrderRecord {
    private Long id;
    private Long supplierId;
    private Integer quantity;
    private String poNumber;
    private LocalDate promisedDeliveryDate;
    private LocalDate issuedDate;

    public PurchaseOrderRecord() {}
    
    public PurchaseOrderRecord(Long id, Long supplierId, Integer quantity, String poNumber, LocalDate promisedDeliveryDate, LocalDate issuedDate) {
        this.id = id;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.poNumber = poNumber;
        this.promisedDeliveryDate = promisedDeliveryDate;
        this.issuedDate = issuedDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getPoNumber() { return poNumber; }
    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }
    public LocalDate getPromisedDeliveryDate() { return promisedDeliveryDate; }
    public void setPromisedDeliveryDate(LocalDate promisedDeliveryDate) { this.promisedDeliveryDate = promisedDeliveryDate; }
    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }
}
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "purchase_order_records",
    uniqueConstraints = @UniqueConstraint(columnNames = "poNumber")
)
public class PurchaseOrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String poNumber;

    @Column(nullable = false)
    private Long supplierId;

    private String itemDescription;

    @Column(nullable = false)
    private Integer quantity;

    private LocalDate promisedDeliveryDate;
    private LocalDate issuedDate;

    public PurchaseOrderRecord() {}

    public PurchaseOrderRecord(String poNumber, Long supplierId,
                               String itemDescription, Integer quantity,
                               LocalDate promisedDeliveryDate, LocalDate issuedDate) {
        this.poNumber = poNumber;
        this.supplierId = supplierId;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.promisedDeliveryDate = promisedDeliveryDate;
        this.issuedDate = issuedDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDate getPromisedDeliveryDate() {
        return promisedDeliveryDate;
    }

}


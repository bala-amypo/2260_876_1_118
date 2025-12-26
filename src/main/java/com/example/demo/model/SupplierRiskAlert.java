package com.example.demo.model;
import jakarta.persistence.*;

public class SupplierRiskAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long supplierId;
    private String alertLevel;
    private Boolean resolved = false;

    public SupplierRiskAlert() {}
    
    public SupplierRiskAlert(Long id, Long supplierId, String alertLevel, Boolean resolved) {
        this.id = id;
        this.supplierId = supplierId;
        this.alertLevel = alertLevel;
        this.resolved = resolved;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public String getAlertLevel() { return alertLevel; }
    public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
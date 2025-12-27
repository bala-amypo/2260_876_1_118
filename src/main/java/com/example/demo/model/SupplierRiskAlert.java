package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "supplier_risk_alert")
public class SupplierRiskAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    private String alertType;
    private String description;
    private Boolean resolved = false;
    private String alertLevel;

    // ✅ REQUIRED no-args constructor
    public SupplierRiskAlert() {
    }

    // ✅ SAFE constructor for tests
    public SupplierRiskAlert(Long supplierId, String alertType,
                             String description, String alertLevel) {
        this.supplierId = supplierId;
        this.alertType = alertType;
        this.description = description;
        this.alertLevel = alertLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public String getAlertType() { return alertType; }
    public void setAlertType(String alertType) { this.alertType = alertType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }

    public String getAlertLevel() { return alertLevel; }
    public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }
}

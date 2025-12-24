package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRiskAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    private String alertLevel; // HIGH, MEDIUM, LOW
    private String message;

    @Builder.Default
    private Boolean resolved = false;

    private LocalDateTime alertDate;

    @PrePersist
    public void onCreate() {
        this.alertDate = LocalDateTime.now();
    }
}

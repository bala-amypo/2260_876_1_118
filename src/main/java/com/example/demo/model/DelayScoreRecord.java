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
public class DelayScoreRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    private Long poId;

    private Integer delayDays;
    private String delaySeverity; // ON_TIME, MINOR, MODERATE, SEVERE
    private Double score;

    private LocalDateTime computedAt;

    @PrePersist
    public void onCreate() {
        this.computedAt = LocalDateTime.now();
    }
}

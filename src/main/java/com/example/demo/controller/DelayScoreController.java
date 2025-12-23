package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.service.DelayScoreService;

@RestController
@RequestMapping("/api/delay-scores")
@Tag(name = "Delay Scores", description = "APIs for managing delay scores of purchase orders")
public class DelayScoreController {

    private final DelayScoreService delayScoreService;

    public DelayScoreController(DelayScoreService delayScoreService) {
        this.delayScoreService = delayScoreService;
    }

    // Compute delay score for a specific PO
    @PostMapping("/compute/{poId}")
    public DelayScoreRecord computeScore(@PathVariable Long poId) {
        return delayScoreService.computeDelayScore(poId);
    }

    // Get all delay scores for a supplier
    @GetMapping("/supplier/{supplierId}")
    public List<DelayScoreRecord> getBySupplier(@PathVariable Long supplierId) {
        return delayScoreService.getScoresBySupplier(supplierId);
    }

    // Get a specific delay score by ID
    @GetMapping("/{id}")
    public DelayScoreRecord getScore(@PathVariable Long id) {
        return delayScoreService.getScoreById(id);
    }

    // Get all delay scores
    @GetMapping("/")
    public List<DelayScoreRecord> getAllScores() {
        return delayScoreService.getAllScores();
    }
}

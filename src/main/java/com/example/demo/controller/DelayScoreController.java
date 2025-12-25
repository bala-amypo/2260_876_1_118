package com.example.demo.controller;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.service.DelayScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delay-scores")
@Tag(name = "Delay Scoring")
public class DelayScoreController {

    private final DelayScoreService delayScoreService;

    public DelayScoreController(DelayScoreService delayScoreService) {
        this.delayScoreService = delayScoreService;
    }

    @PostMapping("/compute/{poId}")
    @Operation(summary = "Compute delay score for a PO")
    public ResponseEntity<DelayScoreRecord> compute(@PathVariable Long poId) {
        return ResponseEntity.ok(delayScoreService.computeDelayScore(poId));
    }

    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get scores by supplier")
    public ResponseEntity<List<DelayScoreRecord>> getBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(delayScoreService.getScoresBySupplier(supplierId));
    }

    @GetMapping
    @Operation(summary = "List all scores")
    public ResponseEntity<List<DelayScoreRecord>> list() {
        return ResponseEntity.ok(delayScoreService.getAllScores());
    }
}

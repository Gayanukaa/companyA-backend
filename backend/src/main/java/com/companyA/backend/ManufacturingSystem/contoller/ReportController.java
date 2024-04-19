package com.companyA.backend.ManufacturingSystem.controller;

import com.companyA.backend.ManufacturingSystem.model.Report;
import com.companyA.backend.ManufacturingSystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{createdBy}")
    public ResponseEntity<List<Report>> getReportsCreatedBy(@PathVariable String createdBy) {
        List<Report> reports = reportService.getReportsCreatedBy(createdBy);
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/create")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report createdReport = reportService.createReport(report);
        return ResponseEntity.ok(createdReport);
    }

    // Add other endpoints for report management as needed
}

package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.Report;
import com.companyA.backend.ManufacturingSystem.service.ManufacturingReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ManufactureReportController {

    private final ManufacturingReportService manufacturingReportService;

    @Autowired
    public ManufactureReportController(ManufacturingReportService manufacturingReportService) {
        this.manufacturingReportService = manufacturingReportService;
    }

    @GetMapping("/{createdBy}")
    public ResponseEntity<List<Report>> getReportsCreatedBy(@PathVariable String createdBy) {
        List<Report> reports = manufacturingReportService.getReportsCreatedBy(createdBy);
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/create")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report createdReport = manufacturingReportService.createReport(report);
        return ResponseEntity.ok(createdReport);
    }

    // Add other endpoints for report management as needed
}

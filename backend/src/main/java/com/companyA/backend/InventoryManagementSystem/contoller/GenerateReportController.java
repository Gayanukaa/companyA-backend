package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.GenerateReport;
import com.companyA.backend.InventoryManagementSystem.service.GenerateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invReports")
@CrossOrigin
public class GenerateReportController {

    private final GenerateReportService generateReportService;

    @Autowired
    public GenerateReportController(GenerateReportService generateReportService) {
        this.generateReportService = generateReportService;
    }

    //Generate Report
    @PostMapping("/generate")
    public ResponseEntity<String> createReport(GenerateReport report) {
        String sup =  generateReportService.createReport(report);
        return ResponseEntity.status(HttpStatus.OK).body(sup);
    }

    //Get all the reports
    @GetMapping("/details")
    public List<GenerateReport> reportDetails(){
        return generateReportService.reportDetails();
    }

    //Get a specific report by report Id
    @GetMapping("/getReport/{id}")
    public GenerateReport getInvReportById(@PathVariable String id){
        return generateReportService.getInvReportById(id);
    }

    //Delete a report by report Id
    @DeleteMapping("/deleteReport/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable String reportId) {
        return new ResponseEntity<String>(generateReportService.deleteReportById(reportId),HttpStatus.OK);
    }


}


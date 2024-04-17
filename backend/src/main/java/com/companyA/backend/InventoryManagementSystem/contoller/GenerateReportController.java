package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.GenerateReport;
import com.companyA.backend.InventoryManagementSystem.model.InventoryManager;
import com.companyA.backend.InventoryManagementSystem.model.Suppliers;
import com.companyA.backend.InventoryManagementSystem.service.GenerateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class GenerateReportController {
    @Autowired
    private GenerateReportService generateReportService;

    @PostMapping("/generate")
    public ResponseEntity<String> createReport(GenerateReport report) {
        String sup =  generateReportService.createReport(report);
        return ResponseEntity.status(HttpStatus.OK).body(sup);
    }


    @GetMapping("/details")
    public List<GenerateReport> reportDetails(){
        return generateReportService.reportDetails();
    }

    @GetMapping("/getReport/{id}")
    public GenerateReport getReportById(@PathVariable String id){
        return generateReportService.getReportById(id);
    }

    @DeleteMapping("/deleteReport/{reportId}")
    public ResponseEntity<String> deleteReport(@PathVariable String reportId) {
        return new ResponseEntity<String>(generateReportService.deleteReportById(reportId),HttpStatus.OK);
    }


}


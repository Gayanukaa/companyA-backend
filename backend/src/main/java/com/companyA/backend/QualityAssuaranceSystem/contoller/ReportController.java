package com.companyA.backend.QualityAssuaranceSystem.contoller;


import com.companyA.backend.QualityAssuaranceSystem.model.Report;
import com.companyA.backend.QualityAssuaranceSystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/SmpReports")
@CrossOrigin
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/getReport/{id}")
    public Optional<Report> getSampleReportById(@PathVariable String id) {
        return reportService.getSampleReportById(id);
    }

    @PostMapping("/prototypes/generate")
    public ResponseEntity<Report> generatePrototypeReport() {
        Report result = reportService.generatePrototypeReport();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/samples/generate")
    public ResponseEntity<Report> generateSampleReport() {
        Report result = reportService.generateSampleReport();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReport(@PathVariable String id){
        return reportService.deleteReportById(id);
    }
}

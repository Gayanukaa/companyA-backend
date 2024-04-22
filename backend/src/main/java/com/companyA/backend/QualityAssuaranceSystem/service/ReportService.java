package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Report;
import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.model.TestSubjects;
import com.companyA.backend.QualityAssuaranceSystem.repository.PrototypeRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.ReportRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PrototypeRepository prototypeRepository;

    @Autowired
    private SampleRepository sampleRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getSampleReportById(String id) {
        return reportRepository.findById(id);
    }

    public String generatePrototypeReport() {
        List<Prototype> prototypes = prototypeRepository.findAll();
        return generateReport(prototypes, "Prototype");
    }

    public String generateSampleReport() {
        List<Sample> samples = sampleRepository.findAll();
        return generateReport(samples, "Sample");
    }

    public String generateReport(List<? extends TestSubjects> subjects, String type) {
        StringBuilder reportContent = new StringBuilder();

        for (TestSubjects subject : subjects) {
            reportContent.append(type).append(" ID: ").append(subject.getId()).append("\n");
            reportContent.append("Test Name: ").append(subject.getExpectedTest() != null ? subject.getExpectedTest() : "Unknown").append("\n");
            reportContent.append("Received Date: ").append(subject.getReceivedDate() != null ? subject.getReceivedDate() : "Unknown").append("\n");
            reportContent.append("Test Status: ").append(subject.getTestStatus() != null ? subject.getTestStatus() : "Unknown").append("\n");
            reportContent.append("\n");
        }

        String reportId = "R" + UUID.randomUUID().toString().substring(0, 6);
        Report report = new Report();
        report.setId(reportId);
        report.setReportType(type);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        report.setGeneratedDateAndTime(formattedDateTime);
        report.setReportContent(reportContent.toString());
        reportRepository.save(report);

        return "Report generated and saved successfully! Report ID: " + reportId;
    }


    public String deleteReportById(String id){
//        reportRepository.deleteById(id);
//        return id +" report successfully deleted";
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            reportRepository.deleteById(id);
            return "Report with ID " + id + " successfully deleted";
        } else {
            return "Report with ID " + id + " not found";
        }
    }

}

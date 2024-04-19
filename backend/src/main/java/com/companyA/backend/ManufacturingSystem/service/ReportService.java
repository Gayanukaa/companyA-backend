package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.Report;
import com.companyA.backend.ManufacturingSystem.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getReportsCreatedBy(String createdBy) {
        return reportRepository.findByCreatedBy(createdBy);
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }


}

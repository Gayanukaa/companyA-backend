package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.Report;
import com.companyA.backend.ManufacturingSystem.repository.ManufacturingReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturingReportService {

    private final ManufacturingReportRepository manufacturingReportRepository;

    @Autowired
    public ManufacturingReportService(ManufacturingReportRepository manufacturingReportRepository) {
        this.manufacturingReportRepository = manufacturingReportRepository;
    }

    public List<Report> getReportsCreatedBy(String createdBy) {
        return manufacturingReportRepository.findByCreatedBy(createdBy);
    }

    public Report createReport(Report report) {
        return manufacturingReportRepository.save(report);
    }


}

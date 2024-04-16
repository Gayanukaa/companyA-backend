package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Report;
import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.ReportRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Only a few methods have been declared so far
//Make necessary changes

@Service
public class SampleService {
    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private ReportRepository reportRepository;

    public List<Sample> getAllSamples() {
        return sampleRepository.findAll();
    }

    public Optional<Sample> getSampleById(String id) {
        return sampleRepository.findById(id);
    }

    public String createSample(Sample sample) {
        String tempid = sample.getId();
        Optional<Sample> AvalilablePrototype = sampleRepository.findById(tempid);
        if (AvalilablePrototype.isPresent()) {
            return "The sample with id: "+tempid +" is already created.";
        }

        else {
            sampleRepository.save(sample);
            return "sample added Successfully";
        }
    }

    public Sample addSample(Sample sample) {
        return sampleRepository.save(sample);
    }

    public String testSample(Sample sample, Test test){
        String tempid = sample.getId();
        Optional<Sample> AvalilableSample = sampleRepository.findById(tempid);
        if (AvalilableSample.isPresent()) {
            sample.setAllocatedTest(test);
            sampleRepository.save(sample);
            return "The sample with id: "+tempid +" is subjected to test:"+ test.getTestId();
        }

        else {
            return "Test was not initiated";
        }

    }


    public String generateReport() {
        List<Sample> samples = getAllSamples();
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Samples Report:\n");

        for (Sample sample : samples) {
            reportContent.append("Sample ID: ").append(sample.getId()).append("\n");
            reportContent.append("Test Name: ").append(sample.getTestName() != null ? sample.getTestName() : "Unknown").append("\n");
            reportContent.append("Received Date: ").append(sample.getReceivedDate() != null ? sample.getReceivedDate() : "Unknown").append("\n");
            reportContent.append("Test Status: ").append(sample.getTestStatus() != null ? sample.getTestStatus() : "Unknown").append("\n");
            reportContent.append("\n");
        }

        String reportId = UUID.randomUUID().toString().substring(0, 8);

        Report report = new Report();
        report.setId(reportId);
        report.setReportContent(reportContent.toString());
        reportRepository.save(report);

        return "Report generated and saved successfully! Report ID: " + reportId;
    }
}

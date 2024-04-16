package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import com.companyA.backend.QualityAssuaranceSystem.model.Report;
import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.PrototypeRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.ReportRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Only a few methods have been declared so far
//Make necessary changes

@Service
public class PrototypeService {

    @Autowired
    private PrototypeRepository prototypeRepository;
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ReportRepository reportRepository;

    public List<Prototype> getAllPrototypes() {
        return prototypeRepository.findAll();
    }

    public Optional<Prototype> getPrototypeById(String id) {
        return prototypeRepository.findById(id);
    }

    public String createPrototype(Prototype prototype) {
        String tempid = prototype.getId();
        Optional<Prototype> AvalilablePrototype = prototypeRepository.findById(tempid);
        if (AvalilablePrototype.isPresent()) {
            return "The prototype with id: "+tempid +" is already created.";
        }

        else {
            prototypeRepository.save(prototype);
            return "Prototype added Successfully";
        }
    }

           // creating and adding a prototype is slightly different
    public Prototype addPrototype(Prototype prototype) {
        return prototypeRepository.save(prototype);
    }

    public String testPrototype(Prototype prototype,Test test){
        String tempid = prototype.getId();
        String temptestid = test.getTestId();
        Optional<Prototype> AvalilablePrototype = prototypeRepository.findById(tempid);
        Optional<Test> AvalilableTest = testRepository.findById(temptestid);
        if (AvalilablePrototype.isPresent()&&AvalilableTest.isPresent()) {
            prototype.setAllocatedTest(test);
            prototypeRepository.save(prototype);
            return "The prototype with id: "+tempid +" is subjected to test:"+ test.getTestId();
        }

        else {
            return "Test was not initiated";
        }

    }


    public String generateReport() {
        List<Prototype> prototypes = getAllPrototypes();
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Prototypes Report:\n");

        for (Prototype prototype : prototypes) {
            reportContent.append("Prototype ID: ").append(prototype.getId()).append("\n");
            reportContent.append("Test Name: ").append(prototype.getTestName() != null ? prototype.getTestName() : "Unknown").append("\n");
            reportContent.append("Received Date: ").append(prototype.getReceivedDate() != null ? prototype.getReceivedDate() : "Unknown").append("\n");
            reportContent.append("Test Status: ").append(prototype.getTestStatus() != null ? prototype.getTestStatus() : "Unknown").append("\n");
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

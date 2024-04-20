package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.ReportRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.SampleRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SampleService {
    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private TestRepository testRepository;

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
        String temptestid = test.getTestId();
        Optional<Sample> AvalilablePrototype = sampleRepository.findById(tempid);
        Optional<Test> AvalilableTest = testRepository.findById(temptestid);
        if (AvalilablePrototype.isPresent()&&AvalilableTest.isPresent()) {
            String currentStatus = AvalilablePrototype.get().getTestStatus();
            if (!currentStatus.equals("Test initiated")) {
                sample.setAllocatedTest(test);
                sample.setTestStatus("Test initiated");
                sampleRepository.save(sample);
                return "The sample with id: " + tempid + " is subjected to : " + test.getName();
            }
            else return " Previous Test hasn't been finished. Test was not initiated " ;
        }

        else {
            return "Invalid parameters. Test was not initiated";
        }

    }

    public String updateTestMethodById(String sampleId, String newTestName) {
        Sample outdatedSample = sampleRepository.findById(sampleId).orElse(null);
        if(outdatedSample != null) {
            if (!outdatedSample.getTestStatus().equals("Test initiated")) {
                sampleRepository.deleteById(sampleId);
                outdatedSample.setTestName(newTestName);
                sampleRepository.save(outdatedSample);
                return "Test method successfully changed";
            }
            else return "Testing process already started. Test method cannot be changed";
        }
        else return "invalid request";
    }
}

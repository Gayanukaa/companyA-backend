package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.ReportRepository;
import com.companyA.backend.QualityAssuaranceSystem.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

}

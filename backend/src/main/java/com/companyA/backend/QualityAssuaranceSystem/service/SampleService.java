package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Only a few methods have been declared so far
//Make necessary changes

@Service
public class SampleService {
    @Autowired
    private SampleRepository repository;

    public List<Sample> getAllSamples() {
        return repository.findAll();
    }

    public Optional<Sample> getSampleById(String id) {
        return repository.findById(id);
    }

    public Sample addSample(Sample sample) {
        return repository.save(sample);
    }
}

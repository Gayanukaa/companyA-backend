package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Optional<Test> getTestById(String id) {
        return testRepository.findById(id);
    }

    public Test addTest(Test test) {
        return testRepository.save(test);
    }

    public String deleteTestById(String id){
        Optional<Test> test = testRepository.findById(id);
        if (test.isPresent()) {
            testRepository.deleteById(id);
            return "Test with ID " + id + " successfully deleted";
        } else {
            return "Test with ID " + id + " not found";
        }
    }

}

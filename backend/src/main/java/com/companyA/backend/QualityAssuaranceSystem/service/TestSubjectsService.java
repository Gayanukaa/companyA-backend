package com.companyA.backend.QualityAssuaranceSystem.service;


import com.companyA.backend.QualityAssuaranceSystem.model.TestSubjects;
import com.companyA.backend.QualityAssuaranceSystem.repository.TestSubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestSubjectsService {
    @Autowired
    private TestSubjectsRepository testSubjectsRepository;

    public String updateTestMethodById(TestSubjects testSubjects, String newTestName) {
        String testSubjectsId = testSubjects.getId();
        testSubjectsRepository.deleteById(testSubjectsId);
        testSubjects.setTestName(newTestName);
        testSubjectsRepository.save(testSubjects);
        return "Test method successfully changed";
    }

}
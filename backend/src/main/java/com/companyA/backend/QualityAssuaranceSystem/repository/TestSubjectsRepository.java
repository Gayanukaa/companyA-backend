package com.companyA.backend.QualityAssuaranceSystem.repository;

import com.companyA.backend.QualityAssuaranceSystem.model.TestSubjects;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestSubjectsRepository<T extends TestSubjects> extends MongoRepository<T, String> {
    public String checkStatus(TestSubjects testSubjects);
}


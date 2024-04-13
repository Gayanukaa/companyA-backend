package com.companyA.backend.QualityAssuaranceSystem.repository;

import com.companyA.backend.QualityAssuaranceSystem.model.TestSubjects;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSubjectsRepository<T extends TestSubjects> extends MongoRepository<T, String> {
    //public String checkStatus(TestSubjects testSubjects);
    //public String updateTestMethodById(TestSubjects testSubjects, String newTestName);
    //T findbyId(String id);
    //T findbyName(String name);
    //boolean existsById(String id);
}


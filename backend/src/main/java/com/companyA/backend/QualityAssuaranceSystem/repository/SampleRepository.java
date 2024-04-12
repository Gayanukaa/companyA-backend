package com.companyA.backend.QualityAssuaranceSystem.repository;

import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SampleRepository extends TestSubjectsRepository {
    public String testSample(Test test);
}


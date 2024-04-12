package com.companyA.backend.QualityAssuaranceSystem.repository;

import com.companyA.backend.QualityAssuaranceSystem.model.Prototype;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrototypeRepository extends TestSubjectsRepository<Prototype> {
    public String testPrototype(Test test);
}


package com.companyA.backend.QualityAssuaranceSystem.repository;

import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends MongoRepository<Test, String> {

}

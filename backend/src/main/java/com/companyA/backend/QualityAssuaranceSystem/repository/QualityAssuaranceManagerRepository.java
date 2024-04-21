package com.companyA.backend.QualityAssuaranceSystem.repository;

import com.companyA.backend.QualityAssuaranceSystem.model.QualityAssuaranceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityAssuaranceManagerRepository extends MongoRepository<QualityAssuaranceManager,String> {
}

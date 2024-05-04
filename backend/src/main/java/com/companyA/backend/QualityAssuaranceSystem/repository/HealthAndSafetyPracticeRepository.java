package com.companyA.backend.QualityAssuaranceSystem.repository;

import com.companyA.backend.QualityAssuaranceSystem.model.HealthAndSafetyPractice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthAndSafetyPracticeRepository extends MongoRepository<HealthAndSafetyPractice, String> {
}

package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.OverseasExperience;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OverseasExperienceRepository extends MongoRepository<OverseasExperience,String> {
}

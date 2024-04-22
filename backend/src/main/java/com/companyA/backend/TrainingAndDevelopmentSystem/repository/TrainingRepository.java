package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.Training;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingRepository extends MongoRepository<Training,String> {
}

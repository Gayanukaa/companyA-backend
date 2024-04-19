package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.TrainingEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingEmployeeRepository extends MongoRepository<TrainingEmployee,String> {
}

package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.Prototyping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrototypingRepository extends MongoRepository<Prototyping,String > {
}

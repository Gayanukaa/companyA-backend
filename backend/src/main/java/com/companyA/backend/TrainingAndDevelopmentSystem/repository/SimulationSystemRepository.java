package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.SimulationSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SimulationSystemRepository extends MongoRepository<SimulationSystem,String> {
}

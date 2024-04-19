package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.UnitSupervisor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnitSupervisorRepository extends MongoRepository<UnitSupervisor,String > {
}

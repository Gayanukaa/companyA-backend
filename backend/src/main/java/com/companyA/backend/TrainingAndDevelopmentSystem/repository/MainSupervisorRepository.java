package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.MainSupervisor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MainSupervisorRepository extends MongoRepository<MainSupervisor,String> {
}

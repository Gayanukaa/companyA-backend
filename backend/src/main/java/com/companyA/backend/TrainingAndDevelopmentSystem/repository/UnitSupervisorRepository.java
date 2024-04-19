package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.UnitSupervisor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UnitSupervisorRepository extends MongoRepository<UnitSupervisor,String > {
    @Query("{'uSupervisorEmail': ?0, 'uPassword': ?1}")
    UnitSupervisor findByEmailAndPassword(String email, String password);
}

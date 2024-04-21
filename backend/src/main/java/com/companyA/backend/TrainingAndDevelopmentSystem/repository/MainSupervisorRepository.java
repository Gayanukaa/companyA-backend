package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.MainSupervisor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MainSupervisorRepository extends MongoRepository<MainSupervisor,String> {
    @Query("{'mSupervisorEmail': ?0, 'mPassword': ?1}")
    MainSupervisor findByEmailAndPassword(String email, String password);
}

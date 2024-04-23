package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.TrainingEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TrainingEmployeeRepository extends MongoRepository<TrainingEmployee,String> {
    @Query("{ 'employeeEmail' : ?0, 'password' : ?1 }")
    TrainingEmployee findByEmailAndPassword(String email, String password);

    boolean existsByEmployeeEmail(String email);

    TrainingEmployee findByEmployeeEmail(String email);
}

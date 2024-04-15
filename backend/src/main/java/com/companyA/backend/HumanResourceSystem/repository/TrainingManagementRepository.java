package com.companyA.backend.HumanResourceSystem.repository;

import com.companyA.backend.HumanResourceSystem.model.TrainingManagementModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingManagementRepository extends MongoRepository<TrainingManagementModel,String> {
    Optional<TrainingManagementModel> findById(String trainingId);
}
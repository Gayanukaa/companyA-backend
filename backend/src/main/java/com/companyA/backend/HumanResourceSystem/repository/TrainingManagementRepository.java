package com.companyA.backend.HumanResourceSystem.repository;

import com.companyA.backend.HumanResourceSystem.model.TrainingManagementModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingManagementRepository extends MongoRepository<TrainingManagementModel,String> {
    TrainingManagementModel findById(String id);




}

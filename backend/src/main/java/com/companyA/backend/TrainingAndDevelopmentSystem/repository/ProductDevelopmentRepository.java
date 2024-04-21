package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.ProductDevelopment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDevelopmentRepository extends MongoRepository<ProductDevelopment,String> {
}

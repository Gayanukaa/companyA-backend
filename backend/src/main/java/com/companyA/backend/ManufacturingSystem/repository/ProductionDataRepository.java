package com.companyA.backend.ManufacturingSystem.repository;

import com.companyA.backend.ManufacturingSystem.model.ProductionData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository interface for CRUD operations on ProductionData entities
@Repository
public interface ProductionDataRepository extends MongoRepository<ProductionData, String> {
    // Method to find production data by lineId
    List<ProductionData> findByLineId(String lineId);
}
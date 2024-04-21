package com.companyA.backend.ManufacturingSystem.repository;

import com.companyA.backend.ManufacturingSystem.model.MaterialRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository interface for MaterialRequest entities
@Repository
public interface MaterialRequestRepository extends MongoRepository<MaterialRequest, ObjectId> {
    // Method to find a material request by material ID and quantity
    Optional<MaterialRequest> findByMaterialIdAndQuantity(Integer materialId, Integer quantity);

    // Method to delete a material request by material ID and quantity
    void deleteByMaterialIdAndQuantity(Integer materialId, Integer quantity);
}
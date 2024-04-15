package com.companyA.backend.ManufacturingSystem.repository;

import com.companyA.backend.ManufacturingSystem.model.MaintenanceRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

// Repository interface for CRUD operations on MaintenanceRequest entities
public interface MaintenanceRequestRepository extends MongoRepository<MaintenanceRequest, ObjectId> {
    // Custom query method to find maintenance requests by equipment name
    List<MaintenanceRequest> findByEquipmentName(String equipmentName);
}



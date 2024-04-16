package com.companyA.backend.ManufacturingSystem.repository;

import com.companyA.backend.ManufacturingSystem.model.MaintenanceRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository interface for CRUD operations on MaintenanceRequest entities
@Repository
public interface MaintenanceRequestRepository extends MongoRepository<MaintenanceRequest, ObjectId> {
    Optional<MaintenanceRequest> findByMachineIdAndMaintenanceType(Integer machineId, String maintenanceType);
    void deleteByMachineIdAndMaintenanceType(Integer machineId, String maintenanceType);
}


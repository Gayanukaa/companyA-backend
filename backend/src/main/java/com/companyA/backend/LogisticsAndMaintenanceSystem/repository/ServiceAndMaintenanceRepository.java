package com.companyA.backend.LogisticsAndMaintenanceSystem.repository;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.ServiceAndMaintenance;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Technician;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceAndMaintenanceRepository extends MongoRepository<ServiceAndMaintenance, String> {

    ServiceAndMaintenance findByServiceId(String serviceId);
    void deleteByServiceId(String vehicleId);
}

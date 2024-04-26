package com.companyA.backend.LogisticsAndMaintenanceSystem.repository;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.ServiceAndMaintenance;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Technician;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAndMaintenanceRepository extends MongoRepository<ServiceAndMaintenance, String> {

}

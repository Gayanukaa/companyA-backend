package com.companyA.backend.LogisticsAndMaintenanceSystem.repository;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.Location;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Optional<Vehicle> findVehicleByVehicleId(String vehicleId);

}

package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vehicle;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> allVehicle() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> vehicleById(String vehicleId) {
        return vehicleRepository.findVehicleByVehicleId(vehicleId);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public Optional<String> getLocationById(String vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            return Optional.ofNullable(vehicle.getLocation());
        } else {
            return Optional.empty();
        }
    }
}

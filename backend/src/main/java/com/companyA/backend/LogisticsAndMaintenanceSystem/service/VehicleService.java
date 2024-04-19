package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vehicle;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    public void deleteVehicle(String vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleByVehicleId(vehicleId);

        if (optionalVehicle.isPresent()) {
            vehicleRepository.deleteVehicleByVehicleId(vehicleId);
            System.out.println("Vehicle deleted successfully");

        } else {
            throw new RuntimeException("Vehicle not found with id " + vehicleId);
        }
    }

    public ResponseEntity<Map<String, String>> updateVehicle(String vehicleId, Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicleOptional = vehicleRepository.findVehicleByVehicleId(vehicleId);
        if (existingVehicleOptional.isPresent()) {
            Vehicle existingVehicle = existingVehicleOptional.get();

            if (updatedVehicle.getModel() != null) {
                existingVehicle.setModel(updatedVehicle.getModel());
            }
            if (updatedVehicle.getLocation() != null) {
                existingVehicle.setLocation(updatedVehicle.getLocation());
            }

            existingVehicle.setVehicleStatus(updatedVehicle.isVehicleStatus());

            if (updatedVehicle.getFuelLevel() != null) {
                existingVehicle.setLocation(updatedVehicle.getFuelLevel());
            }

            if (updatedVehicle.getMaintenanceDate() != null) {
                existingVehicle.setMaintenanceDate(updatedVehicle.getMaintenanceDate());
            }
            vehicleRepository.save(existingVehicle);

            Map<String, String> response = new HashMap<>();
            response.put("status", "Vehicle updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Vehicle not found with ID: " + vehicleId);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}

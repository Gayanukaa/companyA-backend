package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.UserDeliveryAddress;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vehicle;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    private MongoTemplate mongoTemplate;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> allVehicle() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> vehicleById(String vehicleId) {
        return vehicleRepository.findVehicleByVehicleId(vehicleId);
    }

    public List<Vehicle> getVehiclesByStatus(boolean vehicleStatus) {
        return vehicleRepository.findVehicleByVehicleStatus(vehicleStatus);
    }

    public void addVehicle(Vehicle vehicle) {
        Optional<Vehicle> existingVehicleOptional = vehicleRepository.findVehicleByVehicleId(vehicle.getVehicleId());
        if (existingVehicleOptional.isPresent()) {
            throw new RuntimeException("Vehicle with ID " + vehicle.getVehicleId() + " already exists");
        } else {
            vehicleRepository.save(vehicle);
        }
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

    public ResponseEntity<Map<String, String>> updateVehicle(String id, Map<String, String> updateData) {
        Optional<Vehicle> existingVehicleOptional = vehicleRepository.findById(id);
        if (existingVehicleOptional.isPresent()) {
            Vehicle existingVehicle = existingVehicleOptional.get();

            // Update the fields based on the provided update data
            updateData.forEach((key, value) -> {
                switch (key) {
                    case "model":
                        existingVehicle.setModel(value);
                        break;
                    case "location":
                        existingVehicle.setLocation(value);
                        break;
                    case "vehicleStatus":
                        existingVehicle.setVehicleStatus(Boolean.parseBoolean(value));
                        break;
                    case "maintenanceDate":
                        existingVehicle.setMaintenanceDate(value);
                        break;
                    case "fuelLevel":
                        existingVehicle.setFuelLevel(value);
                        break;
                    default:
                        // Handle unknown fields or ignore them
                        break;
                }
            });

            vehicleRepository.save(existingVehicle);

            Map<String, String> response = new HashMap<>();
            response.put("status", "Vehicle updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Vehicle not found with ID: " + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, String>> assignVehicleToAddress(String deliveryAddressId) {
        // Retrieve user delivery address from MongoDB collection
        UserDeliveryAddress deliveryAddress = mongoTemplate.findById(deliveryAddressId, UserDeliveryAddress.class);
        if (deliveryAddress == null) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User delivery address not found with ID: " + deliveryAddressId);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Find a vehicle with status set to true
        List<Vehicle> availableVehicles = vehicleRepository.findVehicleByVehicleStatus(true);
        if (!availableVehicles.isEmpty()) {
            // Assign the first available vehicle to the delivery address
            Vehicle assignedVehicle = availableVehicles.get(0);
            assignedVehicle.setLocation(deliveryAddress.getAddressLine1() + ", " + deliveryAddress.getCity() + ", " + deliveryAddress.getState());
            vehicleRepository.save(assignedVehicle);

            Map<String, String> response = new HashMap<>();
            response.put("status", "Vehicle assigned successfully to the address: " + assignedVehicle.getLocation());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "No available vehicles with true status to assign");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}

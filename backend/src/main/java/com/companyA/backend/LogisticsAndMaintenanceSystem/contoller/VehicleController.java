package com.companyA.backend.LogisticsAndMaintenanceSystem.contoller;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vehicle;
import com.companyA.backend.LogisticsAndMaintenanceSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vv2")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/get allVehicle")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.allVehicle(), HttpStatus.OK);
    }

    @GetMapping("/byId/getVehicle/{vehicleId}")
    public ResponseEntity<Optional<Vehicle>> getVehicleById(@PathVariable String vehicleId) {
        return new ResponseEntity<>(vehicleService.vehicleById(vehicleId), HttpStatus.OK);
    }

    @GetMapping("/byId/getVehicleLocation/{vehicleId}")
    public ResponseEntity<String> getVehicleLocationById(@PathVariable String vehicleId) {
        Optional<String> locationOptional = vehicleService.getLocationById(vehicleId);
        return locationOptional.map(s -> new ResponseEntity<>(s, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("Location not found for vehicle ID: " + vehicleId, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Vehicle added successfully");
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }
}





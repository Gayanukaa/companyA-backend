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
@RequestMapping("/api/v1")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/getVehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.allVehicle(), HttpStatus.OK);
    }

    @GetMapping("/byId/getVehicle/{vehicleId}")
    public ResponseEntity<Optional<Vehicle>> getVehicleById(@PathVariable String vehicleId) {
        return new ResponseEntity<>(vehicleService.vehicleById(vehicleId), HttpStatus.OK);
    }

    @GetMapping("/vehiclesByStatus/{status}")
    public ResponseEntity<List<Vehicle>> getVehiclesByStatus(@PathVariable boolean status) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByStatus(status);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }



    @PostMapping("/addVehicle")
    public ResponseEntity<Map<String, String>> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Vehicle added successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateVehicle/{id}")
    public ResponseEntity<Map<String, String>> updateVehicle(@PathVariable("id") String id, @RequestBody Map<String, String> updateData) {
        return vehicleService.updateVehicle(id, updateData);
    }

    @DeleteMapping("/deleteVehicle/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assignVehicleToAddress/{deliveryAddressId}")
    public ResponseEntity<Map<String, String>> assignVehicleToAddress(@PathVariable String deliveryAddressId) {
        return vehicleService.assignVehicleToAddress(deliveryAddressId);
    }

}





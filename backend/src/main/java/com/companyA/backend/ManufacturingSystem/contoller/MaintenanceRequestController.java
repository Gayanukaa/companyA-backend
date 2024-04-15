package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.MaintenanceRequest;
import com.companyA.backend.ManufacturingSystem.service.MaintenanceRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controller class for handling MaintenanceRequest requests
@RestController
@RequestMapping("/api/maintenance")

public class MaintenanceRequestController {
    private final MaintenanceRequestService maintenanceRequestService;

    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestService = maintenanceRequestService;
    }

    // Endpoint to add a new maintenance request
    // Endpoint to add a new maintenance request
    @PostMapping("/addMaintenanceRequest")
    public ResponseEntity<String> addMaintenanceRequest(@RequestBody MaintenanceRequest maintenanceRequest) {
        try {
            MaintenanceRequest addedRequest = maintenanceRequestService.addMaintenanceRequest(maintenanceRequest);
            String successMessage = "Maintenance request with ID " + addedRequest.getId() + " created successfully. Maintenance team notified.";
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to create maintenance request.", HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to delete a maintenance request by ID
    @DeleteMapping("/deleteMaintenanceRequest/{id}")
    public ResponseEntity<String> deleteMaintenanceRequest(@PathVariable("id") String id) {
        try {
            maintenanceRequestService.deleteMaintenanceRequest(id);
            return new ResponseEntity<>("Maintenance request deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to delete maintenance request", HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to find maintenance requests by equipment name
    @GetMapping("/findByEquipmentName/{equipmentName}")
    public ResponseEntity<List<MaintenanceRequest>> findMaintenanceRequestsByEquipmentName(@PathVariable("equipmentName") String equipmentName) {
        // Call the service method to find maintenance requests by equipment name
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestService.findMaintenanceRequestsByEquipmentName(equipmentName);
        return new ResponseEntity<>(maintenanceRequests, HttpStatus.OK);
    }

}


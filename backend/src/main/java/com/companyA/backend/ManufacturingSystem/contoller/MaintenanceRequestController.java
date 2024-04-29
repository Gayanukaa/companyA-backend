package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.MaintenanceRequest;
import com.companyA.backend.ManufacturingSystem.service.MaintenanceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Controller class for handling MaintenanceRequest requests
@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceRequestController {
    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @PostMapping("/addMaintenanceRequest")
    public ResponseEntity<String> addMaintenanceRequest(@RequestBody MaintenanceRequest maintenanceRequest) {
        try {
            MaintenanceRequest response = maintenanceRequestService.addMaintenanceRequest(maintenanceRequest);
            return new ResponseEntity<>("Maintenance request successful.", HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<>("This maintenance request is already existing.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteMaintenanceRequest")
    public ResponseEntity<String> deleteMaintenanceRequest(@RequestBody MaintenanceRequest maintenanceRequest) {
        try {
            maintenanceRequestService.deleteMaintenanceRequest(maintenanceRequest);
            return new ResponseEntity<>("Maintenance request deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Maintenance request deletion failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checkMaintenanceRequest")
    public ResponseEntity<Boolean> checkMaintenanceRequest(@RequestParam Integer machineId, @RequestParam String maintenanceType) {
        return new ResponseEntity<>(maintenanceRequestService.isMaintenanceRequestExists(machineId, maintenanceType), HttpStatus.OK);
    }
}

package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.MaterialRequest;
import com.companyA.backend.ManufacturingSystem.service.MaterialRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controller class for handling MaterialRequest requests
@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class MaterialRequestController {
    private MaterialRequestService materialRequestService;

    // Endpoint to add a new material request
    @PostMapping("/addMaterialRequest")
    public ResponseEntity<String> addMaterialRequest(@RequestBody MaterialRequest materialRequest) {
        try {
            // Attempt to add the material request
            MaterialRequest response = materialRequestService.addMaterialRequest(materialRequest);
            return new ResponseEntity<>("Material request successful. Inventory will be replenished.", HttpStatus.CREATED);

        } catch (RuntimeException e) {
            // Return error response if the material request already exists
            return new ResponseEntity<>("This order is already existing.", HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to delete a material request
    @DeleteMapping("/deleteMaterialRequest")
    public ResponseEntity<String> deleteMaterialRequest(@RequestBody MaterialRequest materialRequest) {
        try {
            // Attempt to delete the material request
            materialRequestService.deleteMaterialRequest(materialRequest);
            return new ResponseEntity<>("Material request deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            // Return error response if deletion fails
            return new ResponseEntity<>("Material request deletion failed", HttpStatus.BAD_REQUEST);
        }
    }
}
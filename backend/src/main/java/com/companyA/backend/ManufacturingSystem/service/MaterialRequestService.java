package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.MaterialRequest;
import com.companyA.backend.ManufacturingSystem.repository.MaterialRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

// Service class for handling MaterialRequest entities
@Service
public class MaterialRequestService {
    private MaterialRequestRepository materialRequestRepository;

    // Method to add a new material request
    public MaterialRequest addMaterialRequest(MaterialRequest materialRequest) {
        // Check if the material request already exists
        Optional<MaterialRequest> existingMaterialRequest = materialRequestRepository.findByMaterialIdAndQuantity(materialRequest.getMaterialId(), materialRequest.getQuantity());
        if (existingMaterialRequest.isPresent()) {
            // Throw an exception if the material request already exists
            throw new RuntimeException("This order is already existing.");
        }
        // Set the current date for the material request
        materialRequest.setDate(LocalDate.now().toString());
        // Save and return the added material request
        return materialRequestRepository.save(materialRequest);
    }

    // Method to delete a material request
    public void deleteMaterialRequest(MaterialRequest materialRequest) {
        // Delete the material request by material ID and quantity
        materialRequestRepository.deleteByMaterialIdAndQuantity(materialRequest.getMaterialId(), materialRequest.getQuantity());
    }
}
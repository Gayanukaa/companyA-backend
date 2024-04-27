package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.Inventory;
import com.companyA.backend.ManufacturingSystem.model.MaterialRequest;
import com.companyA.backend.ManufacturingSystem.repository.MaterialRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialRequestService {

    @Autowired
    private MaterialRequestRepository materialRequestRepository;

    // Method to update material request quantity
    public String updateMaterialRequest(MaterialRequest materialRequest) {
        // Find the stored material request by ID
        Inventory storedMaterialRequest = materialRequestRepository.findById(materialRequest.getId()).orElse(null);
        // Check if material request exists
        if (storedMaterialRequest == null) {
            return "Invalid material ID";
        }
        // Calculate new quantity
        int newQuantity = storedMaterialRequest.getQuantity() - materialRequest.getQuantity();
        // Check if new quantity is valid
        if (newQuantity < 0) {
            return "Current material quantity is not enough in the inventory.";
        }
        // Update quantity and save to repository
        storedMaterialRequest.setQuantity(newQuantity);
        materialRequestRepository.save(storedMaterialRequest);
        return "Material request is successful";
    }
}
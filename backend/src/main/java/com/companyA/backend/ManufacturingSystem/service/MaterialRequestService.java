package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.MaterialRequest;
import com.companyA.backend.ManufacturingSystem.repository.MaterialRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

// Service class for handling MaterialRequest entities
@Service
@AllArgsConstructor
public class MaterialRequestService {
    private MaterialRequestRepository materialRequestRepository;

    public MaterialRequest addMaterialRequest(MaterialRequest materialRequest) {
        Optional<MaterialRequest> existingMaterialRequest = materialRequestRepository.findByMaterialIdAndQuantity(materialRequest.getMaterialId(), materialRequest.getQuantity());
        if (existingMaterialRequest.isPresent()) {
            throw new RuntimeException("This order is already existing.");
        }
        materialRequest.setDate(LocalDate.now().toString());
        return materialRequestRepository.save(materialRequest);
    }

    public void deleteMaterialRequest(MaterialRequest materialRequest) {
        materialRequestRepository.deleteByMaterialIdAndQuantity(materialRequest.getMaterialId(), materialRequest.getQuantity());
    }
}
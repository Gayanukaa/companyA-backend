package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.MaintenanceRequest;
import com.companyA.backend.ManufacturingSystem.repository.MaintenanceRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Optional;

// Service class for handling MaintenanceRequest entities
@Service
@AllArgsConstructor
public class MaintenanceRequestService {
    private MaintenanceRequestRepository maintenanceRequestRepository;

    // Add a new maintenance request
    public MaintenanceRequest addMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        // Check if the maintenance request already exists
        Optional<MaintenanceRequest> existingMaintenanceRequest = maintenanceRequestRepository.findByMachineIdAndMaintenanceType(maintenanceRequest.getMachineId(), maintenanceRequest.getMaintenanceType());
        if (existingMaintenanceRequest.isPresent()) {
            throw new RuntimeException("This maintenance request is already existing.");
        }
        // Set the current date for the maintenance request
        maintenanceRequest.setDate(LocalDate.now().toString());
        // Save the maintenance request to the repository
        return maintenanceRequestRepository.save(maintenanceRequest);
    }

    // Delete a maintenance request
    public void deleteMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        maintenanceRequestRepository.deleteByMachineIdAndMaintenanceType(maintenanceRequest.getMachineId(), maintenanceRequest.getMaintenanceType());
    }

    // Check if a maintenance request exists
    public boolean isMaintenanceRequestExists(Integer machineId, String maintenanceType) {
        Optional<MaintenanceRequest> maintenanceRequest = maintenanceRequestRepository.findByMachineIdAndMaintenanceType(machineId, maintenanceType);
        return maintenanceRequest.isPresent();
    }
}



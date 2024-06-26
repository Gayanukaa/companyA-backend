package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.MaintenanceRequest;
import com.companyA.backend.ManufacturingSystem.repository.MaintenanceRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Optional;

// Service class for handling MaintenanceRequest entities
@Service
public class MaintenanceRequestService {
    @Autowired
    private MaintenanceRequestRepository maintenanceRequestRepository;

    public MaintenanceRequest addMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        Optional<MaintenanceRequest> existingMaintenanceRequest = maintenanceRequestRepository.findByMachineIdAndMaintenanceType(maintenanceRequest.getMachineId(), maintenanceRequest.getMaintenanceType());
        if (existingMaintenanceRequest.isPresent()) {
            throw new RuntimeException("This maintenance request is already existing.");
        }
        maintenanceRequest.setDate(LocalDate.now().toString());
        return maintenanceRequestRepository.save(maintenanceRequest);
    }

    public void deleteMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        maintenanceRequestRepository.deleteByMachineIdAndMaintenanceType(maintenanceRequest.getMachineId(), maintenanceRequest.getMaintenanceType());
    }

    public boolean isMaintenanceRequestExists(Integer machineId, String maintenanceType) {
        Optional<MaintenanceRequest> maintenanceRequest = maintenanceRequestRepository.findByMachineIdAndMaintenanceType(machineId, maintenanceType);
        return maintenanceRequest.isPresent();
    }
}
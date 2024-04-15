package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.MaintenanceRequest;
import com.companyA.backend.ManufacturingSystem.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import java.util.List;

import java.time.LocalDate;

// Service class for handling MaintenanceRequest entities
@Service

public class MaintenanceRequestService {
    private final MaintenanceRequestRepository maintenanceRequestRepository;

    // Method to add a new maintenance request
    @Autowired
    public MaintenanceRequestService(MaintenanceRequestRepository maintenanceRequestRepository) {
        this.maintenanceRequestRepository = maintenanceRequestRepository;
    }

    // Method to set the date
    public MaintenanceRequest addMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        maintenanceRequest.setDate(LocalDate.now().toString());
        return maintenanceRequestRepository.save(maintenanceRequest);
    }

    // Method to delete a maintenance request by ID
    public void deleteMaintenanceRequest(String id) {
        maintenanceRequestRepository.deleteById(new ObjectId(id));
    }

    // Add the method to find maintenance requests by equipment name
    public List<MaintenanceRequest> findMaintenanceRequestsByEquipmentName(String equipmentName) {
        return maintenanceRequestRepository.findByEquipmentName(equipmentName);
    }
}




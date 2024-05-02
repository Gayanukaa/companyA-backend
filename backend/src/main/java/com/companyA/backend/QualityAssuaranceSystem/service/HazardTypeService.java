package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.HazardType;
import com.companyA.backend.QualityAssuaranceSystem.repository.HazardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HazardTypeService {

    @Autowired
    private HazardTypeRepository hazardTypeRepository;

    // Retrieve all HazardTypes
    public List<HazardType> getAllHazardTypes() {
        return hazardTypeRepository.findAll();
    }

    // Get a HazardType by ID
    public Optional<HazardType> getHazardTypeById(String id) {
        return hazardTypeRepository.findById(id);
    }

    // Create a new HazardType
    public HazardType createHazardType(
            String id, String testName, List<String> equipments, List<String> safetyGuidelines,
            String riskLevel, String responsiblePersonContactNo
    ) {
        if (hazardTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("HazardType with ID " + id + " already exists.");
        }

        HazardType practice = new HazardType();
        practice.setId(id);
        practice.setTestName(testName);
        practice.setEquipments(equipments);
        practice.setSafetyGuidelines(safetyGuidelines);
        practice.setRiskLevel(riskLevel);
        practice.setResponsiblePersonContactNo(responsiblePersonContactNo);

        return hazardTypeRepository.save(practice);
    }

    // Update a HazardType
    public HazardType updateHazardType(
            String id, List<String> newEquipments, List<String> newSafetyGuidelines, String newSafetyLevel
    ) {
        Optional<HazardType> existingPractice = hazardTypeRepository.findById(id);
        if (existingPractice.isPresent()) {
            HazardType practice = existingPractice.get();
            practice.setEquipments(newEquipments);
            practice.setSafetyGuidelines(newSafetyGuidelines);
            practice.setRiskLevel(newSafetyLevel);
            return hazardTypeRepository.save(practice);
        } else {
            throw new IllegalArgumentException("HazardType with ID " + id + " not found.");
        }
    }

    // Delete a HazardType by ID
    public String deleteHazardTypeById(String id) {
        if (hazardTypeRepository.existsById(id)) {
            hazardTypeRepository.deleteById(id);
            return "HazardType with ID " + id + " successfully deleted.";
        } else {
            return "HazardType with ID " + id + " not found.";
        }
    }
}

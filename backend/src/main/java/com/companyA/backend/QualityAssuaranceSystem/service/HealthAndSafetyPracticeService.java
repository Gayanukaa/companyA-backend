package com.companyA.backend.QualityAssuaranceSystem.service;

import com.companyA.backend.QualityAssuaranceSystem.model.HealthAndSafetyPractice;
import com.companyA.backend.QualityAssuaranceSystem.repository.HealthAndSafetyPracticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthAndSafetyPracticeService {

    @Autowired
    private HealthAndSafetyPracticeRepository healthAndSafetyPracticeRepository;

    // Retrieve all HealthAndSafetyPractices
    public List<HealthAndSafetyPractice> getAllHealthAndSafetyPractices() {
        return healthAndSafetyPracticeRepository.findAll();
    }

    // Get a HealthAndSafetyPractice by ID
    public Optional<HealthAndSafetyPractice> getHealthAndSafetyPracticeById(String id) {
        return healthAndSafetyPracticeRepository.findById(id);
    }

    // Create a new HealthAndSafetyPractice
    public HealthAndSafetyPractice createHealthAndSafetyPractice(
            String id, String hazardType, List<String> equipments, List<String> safetyGuidelines,
            String safetyLevel, String responsiblePersonContactNo
    ) {
        if (healthAndSafetyPracticeRepository.existsById(id)) {
            throw new IllegalArgumentException("HealthAndSafetyPractice with ID " + id + " already exists.");
        }

        HealthAndSafetyPractice practice = new HealthAndSafetyPractice();
        practice.setId(id);
        practice.setHazardType(hazardType);
        practice.setEquipments(equipments);
        practice.setSafetyGuidelines(safetyGuidelines);
        practice.setSafetyLevel(safetyLevel);
        practice.setResponsiblePersonContactNo(responsiblePersonContactNo);

        return healthAndSafetyPracticeRepository.save(practice);
    }

    // Update a HealthAndSafetyPractice
    public HealthAndSafetyPractice updateHealthAndSafetyPractice(
            String id, List<String> newEquipments, List<String> newSafetyGuidelines, String newSafetyLevel
    ) {
        Optional<HealthAndSafetyPractice> existingPractice = healthAndSafetyPracticeRepository.findById(id);
        if (existingPractice.isPresent()) {
            HealthAndSafetyPractice practice = existingPractice.get();
            practice.setEquipments(newEquipments);
            practice.setSafetyGuidelines(newSafetyGuidelines);
            practice.setSafetyLevel(newSafetyLevel);
            return healthAndSafetyPracticeRepository.save(practice);
        } else {
            throw new IllegalArgumentException("HealthAndSafetyPractice with ID " + id + " not found.");
        }
    }

    // Delete a HealthAndSafetyPractice by ID
    public String deleteHealthAndSafetyPracticeById(String id) {
        if (healthAndSafetyPracticeRepository.existsById(id)) {
            healthAndSafetyPracticeRepository.deleteById(id);
            return "HealthAndSafetyPractice with ID " + id + " successfully deleted.";
        } else {
            return "HealthAndSafetyPractice with ID " + id + " not found.";
        }
    }
}



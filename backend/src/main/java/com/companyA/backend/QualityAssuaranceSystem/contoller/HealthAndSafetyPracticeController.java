package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.HealthAndSafetyPractice;
import com.companyA.backend.QualityAssuaranceSystem.service.HealthAndSafetyPracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/health-safety-practices")
@CrossOrigin
public class HealthAndSafetyPracticeController {

    @Autowired
    private HealthAndSafetyPracticeService healthAndSafetyPracticeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createHealthAndSafetyPractice(@RequestBody HealthAndSafetyPractice request) {
        try {
            if (
                    request.getId() == null || request.getId().isBlank() ||
                            request.getTestName() == null || request.getTestName().isBlank() ||
                            request.getEquipments() == null || request.getEquipments().isEmpty() ||
                            request.getSafetyGuidelines() == null || request.getSafetyGuidelines().isEmpty() ||
                            request.getSafetyLevel() == null || request.getSafetyLevel().isBlank() ||
                            request.getResponsiblePersonContactNo() == null || request.getResponsiblePersonContactNo().isBlank()
            ) {
                return new ResponseEntity<>("All fields are required.", HttpStatus.BAD_REQUEST);
            }

            HealthAndSafetyPractice newPractice = healthAndSafetyPracticeService.createHealthAndSafetyPractice(
                    request.getId(),
                    request.getTestName(),
                    request.getEquipments(),
                    request.getSafetyGuidelines(),
                    request.getSafetyLevel(),
                    request.getResponsiblePersonContactNo()
            );

            return new ResponseEntity<>(newPractice, HttpStatus.CREATED);  // Successful creation

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);  // Handle errors like duplicate ID
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Handle unexpected errors
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<HealthAndSafetyPractice>> getAllHealthAndSafetyPractices() {
        List<HealthAndSafetyPractice> practices = healthAndSafetyPracticeService.getAllHealthAndSafetyPractices();
        return new ResponseEntity<>(practices, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getHealthAndSafetyPracticeById(@RequestParam("id") String id) {
        var practice = healthAndSafetyPracticeService.getHealthAndSafetyPracticeById(id);
        if (practice.isPresent()) {
            return new ResponseEntity<>(practice.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("HealthAndSafetyPractice with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHealthAndSafetyPractice(@RequestParam String id, @RequestBody HealthAndSafetyPractice updatedData) {
        try {
            HealthAndSafetyPractice updatedPractice = healthAndSafetyPracticeService.updateHealthAndSafetyPractice(
                    id,
                    updatedData.getEquipments(),
                    updatedData.getSafetyGuidelines(),
                    updatedData.getSafetyLevel()
            );
            return new ResponseEntity<>(updatedPractice, HttpStatus.OK);  // Successful update
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // If the ID doesn't exist
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHealthAndSafetyPractice(@RequestParam String id) {
        String result = healthAndSafetyPracticeService.deleteHealthAndSafetyPracticeById(id);
        if (result.contains("not found")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);  // Not found response
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

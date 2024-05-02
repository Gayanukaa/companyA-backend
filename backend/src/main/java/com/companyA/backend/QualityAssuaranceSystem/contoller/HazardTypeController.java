package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.HazardType;
import com.companyA.backend.QualityAssuaranceSystem.service.HazardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hazard-type")
@CrossOrigin
public class HazardTypeController {

    @Autowired
    private HazardTypeService hazardTypeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createHazardType(@RequestBody HazardType request) {
        try {
            if (
                    request.getId() == null || request.getId().isBlank() ||
                            request.getTestName() == null || request.getTestName().isBlank() ||
                            request.getEquipments() == null || request.getEquipments().isEmpty() ||
                            request.getSafetyGuidelines() == null || request.getSafetyGuidelines().isEmpty() ||
                            request.getRiskLevel() == null || request.getRiskLevel().isBlank() ||
                            request.getResponsiblePersonContactNo() == null || request.getResponsiblePersonContactNo().isBlank()
            ) {
                return new ResponseEntity<>("All fields are required.", HttpStatus.BAD_REQUEST);
            }

            HazardType newPractice = hazardTypeService.createHazardType(
                    request.getId(),
                    request.getTestName(),
                    request.getEquipments(),
                    request.getSafetyGuidelines(),
                    request.getRiskLevel(),
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
    public ResponseEntity<List<HazardType>> getAllHazardTypes() {
        List<HazardType> practices = hazardTypeService.getAllHazardTypes();
        return new ResponseEntity<>(practices, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getHazardTypeById(@RequestParam("id") String id) {
        var practice = hazardTypeService.getHazardTypeById(id);
        if (practice.isPresent()) {
            return new ResponseEntity<>(practice.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("HazardType with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHazardType(@RequestParam String id, @RequestBody HazardType updatedData) {
        try {
            HazardType updatedPractice = hazardTypeService.updateHazardType(
                    id,
                    updatedData.getEquipments(),
                    updatedData.getSafetyGuidelines(),
                    updatedData.getRiskLevel()
            );
            return new ResponseEntity<>(updatedPractice, HttpStatus.OK);  // Successful update
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // If the ID doesn't exist
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHazardType(@RequestParam String id) {
        String result = hazardTypeService.deleteHazardTypeById(id);
        if (result.contains("not found")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);  // Not found response
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

package com.companyA.backend.QualityAssuaranceSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "HealthAndSafetyPractices")
public class HealthAndSafetyPractice {

    @Id
    @NotBlank(message = "ID is required.")
    private String id;

    @NotBlank(message = "Hazard type is required.")
    private String hazardType;

    @NotBlank(message = "Equipment list is required.")
    private List<String> equipments;

    @NotBlank(message = "Safety guidelines are required.")
    private List<String> safetyGuidelines;

    @NotBlank(message = "Safety level is required.")
    private String safetyLevel;  // Level of safety (e.g., Low, Medium, High)

    @NotBlank(message = "Responsible Contact Number is required.")
    private String responsiblePersonContactNo;  // Contact Number of the person in charge of safety during the test
}



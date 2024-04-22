package com.companyA.backend.QualityAssuaranceSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "QAmanager")

public class QualityAssuaranceManager {
    @Id
    private String id;

    @NotBlank
    private String Name;

    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String email;

    @NotBlank
    private List<TestSubjects> assignedTestSubjects;

}

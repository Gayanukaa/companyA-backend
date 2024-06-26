package com.companyA.backend.QualityAssuaranceSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public abstract class TestSubjects {
    @Id
    private String id;

    @NotBlank(message = "Name of the expected test is needed")
    private String expectedTest;

    @NotBlank(message = "Received Date is Required in 'DD/MM/YYYY' format")
    private String receivedDate;

    private String testStatus = "Received" ;
}

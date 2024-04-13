package com.companyA.backend.QualityAssuaranceSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TestSubjects {
    @Id
    private String id;

    @NotBlank(message = "Name of the expected test is needed")    // do we need this here?
    private String testName;

    @NotBlank(message = "Received Date is Required in 'DDMMYYYY' format")
    private static  String receivedDate;

    private String testStatus = "Recived" ;
}

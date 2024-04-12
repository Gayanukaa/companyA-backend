package com.companyA.backend.QualityAssuaranceSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TestSubjects")
@Getter
@Setter

public class TestSubjects {
    @Id
    private String id;

    @NotBlank(message = "TestName is Required")
    private String testName;

    @NotBlank(message = "Recived Date is Required in 'DDMMYYYY' format")
    private static  String  recivedDate;

}

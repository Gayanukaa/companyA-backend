package com.companyA.backend.QualityAssuaranceSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "test")
@Getter
@Setter

public class Test {
    @Id
    private String testId;

    @NotBlank(message = "TestName is Required")    // do we need this here?
    private String name;

}

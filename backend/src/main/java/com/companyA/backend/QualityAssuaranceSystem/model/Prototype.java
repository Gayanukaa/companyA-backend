package com.companyA.backend.QualityAssuaranceSystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prototype")
@Data
@Getter
@Setter

public class Prototype extends TestSubjects {
    private static final String testSubjectType = "Prototype" ;

    private Test allocatedTest;

}

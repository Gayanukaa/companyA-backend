package com.companyA.backend.QualityAssuaranceSystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sample")
@Data
@Getter
@Setter

public class Sample extends TestSubjects {
    private static final String testSubjectType = "Sample" ;

    private Test allocatedTest;

}
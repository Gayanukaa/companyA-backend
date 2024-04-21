package com.companyA.backend.QualityAssuaranceSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reports")
@Getter
@Setter
public class Report {
    @Id
    private String id;
    private String reportType;
    private String generatedDateAndTime;
    private String reportContent;
}

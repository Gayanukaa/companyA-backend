package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "ManufacturingReports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    private String id;
    private String reportName;
    private Date reportDate;
    private String createdBy;
    private String description;
    private int numberOfOrdersCompleted;
    private int totalQuantityOfProductsCompleted;

}

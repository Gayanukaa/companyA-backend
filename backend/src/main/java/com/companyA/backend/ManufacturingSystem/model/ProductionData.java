package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "production_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionData {
    @Id
    private ObjectId id;;
    private String lineId;
    private String data;
    private String description;
    private int production_output;
    private int downtime;
    private int efficiency;
}
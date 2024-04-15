package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

// Entity class representing production data
@Document(collection = "ProductionData")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionData {
    @Id
    private ObjectId id;           // Unique identifier for production data
    private String lineId;         // Identifier for the production line
    private String data;           // Additional data related to production
    private String description;    // Description of the production line
    private int production_output; // Output of the production line
    private int downtime;          // Downtime of the production line
    private int efficiency;        // Efficiency of the production line
}
package com.companyA.backend.ManufacturingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Represents a material request entity
@Document(collection = "MaterialRequests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialRequest {
    @Id
    private ObjectId id;
    private Integer materialId;
    private Integer quantity;
    private String date;
}
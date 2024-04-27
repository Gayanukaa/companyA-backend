package com.companyA.backend.ManufacturingSystem.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Represents a maintenance request entity
@Document(collection = "MaintenanceRequests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceRequest {
    @Id
    private ObjectId id;
    private Integer machineId;
    private String maintenanceType;
    private String date;
}
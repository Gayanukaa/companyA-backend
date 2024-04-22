package com.companyA.backend.ManufacturingSystem.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Represents a maintenance request entity
@Document(collection = "MaintenanceRequests")
@Data
@AllArgsConstructor
@Getter
@Setter
public class MaintenanceRequest {
    @Id
    private ObjectId id;           // Unique identifier for the maintenance request
    private Integer machineId;     // ID of the machine associated with the maintenance request
    private String maintenanceType;// Type of maintenance requested
    private String date;           // Date of the maintenance request

    public void setDate(String string) {
        this.date = date;               //Sets the date of the maintenance request.
    }
}

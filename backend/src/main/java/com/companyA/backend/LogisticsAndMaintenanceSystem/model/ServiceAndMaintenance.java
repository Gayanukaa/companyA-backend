package com.companyA.backend.LogisticsAndMaintenanceSystem.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "ServicesAndMaintenance")
public class ServiceAndMaintenance {

    @Id
    private ObjectId id;
    private String serviceId;
    private double serviceCost;
    private String technicianId; // Updated to include Technician object


}

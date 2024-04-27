package com.companyA.backend.LogisticsAndMaintenanceSystem.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document ;

@Document (collection =  "Vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Vehicle {

    @Id
    private ObjectId id;
    private String vehicleId;
    private String model;
    private String location;
    @lombok.Getter
    private boolean vehicleStatus;
    private String maintenanceDate;
    private String fuelLevel;


}

package com.companyA.backend.LogisticsAndMaintenanceSystem.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document ;



@Document (collation= "Vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Vehicle {

    private String vehicleId;
    private String location;
    private String model;
    private boolean vehicleStatus;
    private String maintenanceDate;
    private String currentJob;


}

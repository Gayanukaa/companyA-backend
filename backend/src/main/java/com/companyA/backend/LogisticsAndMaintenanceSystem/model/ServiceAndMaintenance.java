package com.companyA.backend.LogisticsAndMaintenanceSystem.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "ServicesAndMaintenance")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceAndMaintenance {

    @Id
    private ObjectId id;
    private String serviceId;
    private double serviceCost;
    private String technicianId; // Updated to include Technician object
    private Date lastServicedDate;


    // Method to assign a technician
   public void assignTechnician(String technicianId) {
        this.technicianId = technicianId;
   }


    // Method to generate service report
    public void generateServiceReport() {
        // Implementation for generating service report
    }

    //Method to indicate service status
    public void serviceStatusUpdate(String serviceID, boolean complete){
        if (complete) {
            generateServiceReport();
            lastServicedDate = new Date();
            System.out.println("Service with " + serviceID + " is completed.");
        }
        else{
            System.out.println("Service with " + serviceID + " is not finished yet.");
        }
    }
}

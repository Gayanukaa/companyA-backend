package com.companyA.backend.LogisticsAndMaintenanceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Technician")
public class Technician {
    @Id
    private ObjectId id;
    private String technicianId;
    private String name;
    private String specialty;
    private int yearsOfExperience;
    private boolean availability;  // Indicates if the technician is currently available for work
    private String currentJob;       // Describes the current job assignment of the technician

    public boolean getAvailability() {
        return availability;
    }

}

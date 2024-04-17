package com.companyA.backend.LogisticsAndMaintenanceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document (collection = "Machinery")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Machinery {
    @Id
    private ObjectId id;
    private String machineId;
    private int machineModel;
    private boolean machineStatus;
    private List<String> maintenance;
    private String currentJob;
    private String machineName;


}

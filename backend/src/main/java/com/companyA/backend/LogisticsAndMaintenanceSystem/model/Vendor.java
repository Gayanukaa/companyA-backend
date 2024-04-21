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
@Document(collection = "Vendor")
public class Vendor {
    @Id
    private ObjectId id;
    private String vendorId;
    private String name;
    private String contactNumber;
    private String address;
    private String email;
}

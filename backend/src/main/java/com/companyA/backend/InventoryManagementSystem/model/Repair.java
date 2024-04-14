package com.companyA.backend.InventoryManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Repair")
public class Repair {
    @Id
    private String id;
    private int quantityToRepair;
    //private String name;
    @DocumentReference
    private Stocks stock;
}


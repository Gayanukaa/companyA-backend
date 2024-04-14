package com.companyA.backend.InventoryManagementSystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Document(collection = "InventoryManager")
@Data
@Getter
@Setter
public class InventoryManager {
    @Id
    private String managerId;
    private String managerName;
    private String managerEmail;
    private String managerPhone;
    @DocumentReference
    private List<Warehouse> warehouses; // List to store warehouses offered
    @DocumentReference
    private List<Suppliers> listOfSuppliers; // List to store list of suppliers offered
}

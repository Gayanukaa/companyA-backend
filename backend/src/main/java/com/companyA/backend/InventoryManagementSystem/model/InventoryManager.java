package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document(collection = "InventoryManager")
@Data
@Getter
@Setter
public class InventoryManager {
    @Id@NotBlank
    private String managerId;
    private String managerName;
    private String managerEmail;
    private String managerPhone;

    private List<String> warehouses; // List to store warehouses offered

    private List<String> listOfSuppliers; // List to store list of suppliers offered
}

package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Document(collection = "InventoryManager")
@Data
@Getter
@Setter
public class InventoryManager {
    @Id @NotBlank
    private String managerId;
    private String managerName;
    private String managerEmail;
    private String managerPhone;
    @DocumentReference
    private Map<String, String> warehouses; // List to store warehouses offered

}

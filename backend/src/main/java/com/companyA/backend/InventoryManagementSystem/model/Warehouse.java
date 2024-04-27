package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document(collection = "warehouse")
public class Warehouse {

    @Id
    private String warehouseId;
    @NotBlank (message = "Warehouse name cannot be blank")
    private String name;
    @NotBlank (message = "Warehouse location cannot be blank")
    private String location;
    @DocumentReference
    private List<Stocks> inventoryList;
}

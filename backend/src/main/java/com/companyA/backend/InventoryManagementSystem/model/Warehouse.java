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

    @Id @NotBlank
    private String warehouseId;
    @NotBlank
    private String name;
    @NotBlank
    private String location;
    @DocumentReference
    private List<Stocks> inventoryList;
}

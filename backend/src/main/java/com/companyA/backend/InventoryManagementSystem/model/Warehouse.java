package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


@Data
@Document(collection = "warehouse")
public class Warehouse {

    @Id @NotBlank
    private ObjectId warehouseId;
    @NotBlank
    private String location;
    @DocumentReference @NotBlank
    private List<Inventory> inventoryList;

    //Anupa use this
    //Check implementation to have a variable to store quantity of each item in the warehouse
    //Because mostly inventory database is updated so warehouseQuantity should be updated accordingly.
    //Check implementation for that
}

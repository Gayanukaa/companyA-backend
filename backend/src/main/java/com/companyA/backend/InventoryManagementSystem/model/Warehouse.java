package com.companyA.backend.InventoryManagementSystem.model;

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
    private String location;
    @DocumentReference
    private List<Inventory> inventoryList;

    //Anupa use this
    //Check implementation to have a variable to store quantity of each item in the warehouse
    //Because mostly inventory database is updated so warehouseQuantity should be updated accordingly.
    //Check implementation for that
}

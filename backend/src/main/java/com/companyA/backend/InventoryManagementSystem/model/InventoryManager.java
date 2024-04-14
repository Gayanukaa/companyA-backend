package com.companyA.backend.InventoryManagementSystem.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Document(collection = "InventoryManager")
@Component
public class InventoryManager {
    private List<Inventory> inventory; // List to store iventory offered
    private List<Warehouse> warehouses; // List to store warehouses offered
    private List<Suppliers> listOfSuppliers; // List to store list of suppliers offered
}

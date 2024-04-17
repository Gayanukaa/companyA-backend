package com.companyA.backend.FinanceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "inventory")
public class Stocks {

    private String id;
    private String warehouseId;
    private String name;
    private int quantity;
    private int thresholdQuantity;
    private int weight;
    private int size;
    private int reorderQuantity;
    private float price;

}

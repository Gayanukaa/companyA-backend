package com.companyA.backend.SalesSystem.model;

import lombok.Data;

@Data
public class UpdateExistingProductData {

    private String itemId;
    private int quantity;
    private double price;
    private String warehouseId;
}

package com.companyA.backend.SalesSystem.model;

import lombok.Data;

@Data
public class StockValidation {
    private String itemId;
    private boolean inStock;

    public StockValidation(String id, boolean stock) {
        this.itemId = id;
        this.inStock = stock;
    }
}

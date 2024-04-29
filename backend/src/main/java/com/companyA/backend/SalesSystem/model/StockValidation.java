package com.companyA.backend.SalesSystem.model;

import lombok.Data;

@Data
public class StockValidation {
    private String itemId;
    private boolean isInStock;
    private double subTotal;
    private int stock;

    public StockValidation(String id, boolean inStock,int stockCount, double total) {
        this.itemId = id;
        this.isInStock = inStock;
        this.stock = stockCount;
        this.subTotal = total;
    }
}

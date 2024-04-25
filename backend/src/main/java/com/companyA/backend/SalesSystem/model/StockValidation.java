package com.companyA.backend.SalesSystem.model;

import lombok.Data;

@Data
public class StockValidation {
    private String itemId;
    private boolean inStock;
    private double subTotal;

    public StockValidation(String id, boolean stock, double total) {
        this.itemId = id;
        this.inStock = stock;
        this.subTotal = total;
    }
}

package com.companyA.backend.SalesSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StockValidationResponse {
    private boolean success;
    private String message;

    public StockValidationResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}

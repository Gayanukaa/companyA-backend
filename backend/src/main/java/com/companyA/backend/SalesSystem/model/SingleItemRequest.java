package com.companyA.backend.SalesSystem.model;

import lombok.Data;

@Data
public class SingleItemRequest {
    private String itemId;
    private int quantity;
}

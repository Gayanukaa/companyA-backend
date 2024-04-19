package com.companyA.backend.SalesSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private String itemId;
    private int quantity;
    private double unitPrice;

}

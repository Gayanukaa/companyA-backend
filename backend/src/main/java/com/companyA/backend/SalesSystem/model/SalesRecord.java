package com.companyA.backend.SalesSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class SalesRecord {

    private String order_ID;
    private String order_date;
    private double order_amount;
    private List<String> components;

}

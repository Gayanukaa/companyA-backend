package com.companyA.backend.HumanResourceSystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "inventory")
public class Supplies extends Inventory {
    private String serialNumber;
    private float purchasePrice;
}

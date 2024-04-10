package com.companyA.backend.InventoryManagementSystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "inventory")
public class Supplies extends Inventory {
}

package com.companyA.backend.InventoryManagementSystem.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "inventory")
public class Stocks extends Inventory {

}
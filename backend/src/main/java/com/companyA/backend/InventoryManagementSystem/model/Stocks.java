package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@Document(collection = "inventory")
public class Stocks extends Inventory {
    @NotBlank
    private float price;
}
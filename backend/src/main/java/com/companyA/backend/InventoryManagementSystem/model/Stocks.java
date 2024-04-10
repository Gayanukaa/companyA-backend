package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "inventory")
public class Stocks extends Inventory {
    @NotBlank
    private float price;

    public Stocks(String id, String warehouseId, String name, int quantityAdd, int weightAdd, int sizeAdd, int reorderQuantityAdd, StateOfProduct stateOfProductAdd, InventoryType inventoryTypeAdd, float priceAdd) {
        super(id, warehouseId, name, quantityAdd, weightAdd, sizeAdd, reorderQuantityAdd, stateOfProductAdd, inventoryTypeAdd);
        this.price = priceAdd;
    }
}
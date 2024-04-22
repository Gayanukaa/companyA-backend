package com.companyA.backend.GeneralManagementSystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;



import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "inventory")
@Getter
@Setter

public class Inventory {

    @Id
    private String id;

    @NotBlank(message = "Price is Required")
    private String price;

    @NotBlank(message = "Warehouse Id is Required")
    private String warehouseId;

    @NotBlank(message = "Name of the product is Required")
    private String name;

    @NotBlank(message = "Quantity is Required")
    private String quantity;

    @NotBlank(message = "Weight is Required")
    private String weight;

    @NotBlank(message = "Size is Required")
    private String size;

    @NotBlank(message = "Reorder Quantity is Required")
    private String reorderQuantity;

    @NotBlank(message = "state Of Product  is Required")
    private String stateOfProduct;

    @NotBlank(message = "inventoryType  is Required")
    private String inventoryType;


}

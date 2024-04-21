package com.companyA.backend.InventoryManagementSystem.model;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Repair")

public class Repair {
    @Id @NotBlank
    private String id;
    @NotBlank
    private String inventoryId;
    @NotBlank
    private String name;
    @NotBlank
    private int quantity;

}


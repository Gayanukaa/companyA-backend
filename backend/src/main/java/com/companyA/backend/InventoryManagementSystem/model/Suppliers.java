package com.companyA.backend.InventoryManagementSystem.model;

import java.util.List;
import java.util.Map;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Suppliers")

public class Suppliers {
    @Id
    private String id;
    private String companyName;
    private String contactInfo;
    private List<String> productList; // List to store products offered
}

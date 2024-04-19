package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Document(collection = "GenerateReport")
public class GenerateReport {
    @Id @NotBlank
    private String reportId; //Report Id
    private Map<String, List<String>> mostRemainingItemsByWarehouse; //Most remaining items of each warehouse
    private Map<String, Double> totalWorth; //Total worth of the items in each warehouse
    private Map<String, Map<String, String>> warehouseItemsByWarehouse; //Warehouse Id and items the warehouse store
    private String generatedDateAndTime; //Date and time each report is generated
    private Map<String, String> warehouses; //Warehouse Ids and warehoues names




}

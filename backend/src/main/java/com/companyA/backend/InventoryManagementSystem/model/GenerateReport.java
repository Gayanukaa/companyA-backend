package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
    private String reportId;

    private Map<String, List<String>> mostSoldItemsByWarehouse;
    private Map<String, Double> revenue;
    private Map<String, Map<String, String>> warehouseItemsByWarehouse;
    //private LocalDateTime generatedDateAndTime;
    private Map<String, String> warehouses;




}

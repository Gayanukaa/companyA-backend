package com.companyA.backend.GeneralManagementSystem.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GraphsDataDTO {
    private Map<String, Integer> inventoryData;
    private Map<String, Double> salesData;
    private Map<String, Integer> warehouseData;
}

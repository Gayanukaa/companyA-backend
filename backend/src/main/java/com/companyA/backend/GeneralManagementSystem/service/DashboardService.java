package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.DTO.GraphsDataDTO;
import com.companyA.backend.GeneralManagementSystem.DTO.InventoryDataDTO;
import com.companyA.backend.InventoryManagementSystem.model.Inventory;
import com.companyA.backend.InventoryManagementSystem.repository.InventoryRepository;
import com.companyA.backend.SalesSystem.model.FinanceSalesTableBody;
import com.companyA.backend.SalesSystem.repository.FinanceSalesTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DashboardService {

    private final InventoryRepository inventoryRepository;
    private final FinanceSalesTableRepository financeSalesTableRepository;

    public GraphsDataDTO getGraphsData() {
        GraphsDataDTO graphsDataDTO = new GraphsDataDTO();

        List<Inventory> inventoryExistList = inventoryRepository.findAll();
        List<FinanceSalesTableBody> financeSalesTableBodyExistList = financeSalesTableRepository.findAll();

        Map<String, Integer> inventoryFilteredData = inventoryExistList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toMap(InventoryDataDTO::getName, InventoryDataDTO::getQuantity));

        Map<String, Double> totalOrderAmountPerDay = financeSalesTableBodyExistList.stream()
                .collect(Collectors.groupingBy(FinanceSalesTableBody::getOrder_date,
                        Collectors.summingDouble(FinanceSalesTableBody::getOrder_amount)));

        graphsDataDTO.setInventoryData(inventoryFilteredData);
        graphsDataDTO.setSalesData(totalOrderAmountPerDay);
        return graphsDataDTO;
    }


    private InventoryDataDTO mapToDTO(Inventory inventoryData) {
        InventoryDataDTO inventoryDTO = new InventoryDataDTO();
        inventoryDTO.setName(inventoryData.getName());
        inventoryDTO.setQuantity(Integer.valueOf(String.valueOf(inventoryData.getQuantity())));
        return inventoryDTO;
    }
    public long countInventoryItems(){
        return inventoryRepository.count();
    }

}

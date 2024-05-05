package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.DTO.GraphsDataDTO;
import com.companyA.backend.GeneralManagementSystem.DTO.InventoryDataDTO;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.repository.InventoryRepository;
import com.companyA.backend.InventoryManagementSystem.repository.WarehouseRepository;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.VehicleRepository;
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

    private final InventoryRepository<Stocks> inventoryRepository;
    private final FinanceSalesTableRepository financeSalesTableRepository;
    private final WarehouseRepository warehouseRepository;


    public GraphsDataDTO getGraphsData() {
        GraphsDataDTO graphsDataDTO = new GraphsDataDTO();

        try {
            // Fetching the current data
            List<Stocks> inventoryExistList = inventoryRepository.findAll();
            List<FinanceSalesTableBody> financeSalesTableBodyExistList = financeSalesTableRepository.findAll();
            List<Warehouse> stocksInEachWareHouseExistList = warehouseRepository.findAll();

            // Data Fetching from Inventory
            Map<String, Integer> inventoryFilteredData = inventoryExistList.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toMap(InventoryDataDTO::getName, InventoryDataDTO::getQuantity));

            // Data Fetching from Sales
            Map<String, Double> totalOrderAmountPerDay = financeSalesTableBodyExistList.stream()
                    .collect(Collectors.groupingBy(FinanceSalesTableBody::getOrder_date,
                            Collectors.summingDouble(FinanceSalesTableBody::getOrder_amount)));

            // Data Fetching from warehouses
            Map<String, Integer> stocksInEachWareHouse = stocksInEachWareHouseExistList.stream()
                    .collect(Collectors.toMap(Warehouse::getName, warehouse -> warehouse.getInventoryList().size()));


            graphsDataDTO.setInventoryData(inventoryFilteredData);
            graphsDataDTO.setSalesData(totalOrderAmountPerDay);
            graphsDataDTO.setWarehouseData(stocksInEachWareHouse);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return graphsDataDTO;
    }


    private InventoryDataDTO mapToDTO(Stocks inventoryData) {
        InventoryDataDTO inventoryDTO = new InventoryDataDTO();
        inventoryDTO.setName(inventoryData.getName());
        inventoryDTO.setQuantity(Integer.valueOf(String.valueOf(inventoryData.getQuantity())));
        return inventoryDTO;
    }

    public long countInventoryItems(){
        return inventoryRepository.count();
    }

    private final VehicleRepository vehicleRepository;
    public long countVehicles(){
        return vehicleRepository.count();
    }

}

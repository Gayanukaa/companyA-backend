package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.DTO.GraphsDataDTO;
import com.companyA.backend.GeneralManagementSystem.DTO.InventoryDataDTO;
import com.companyA.backend.GeneralManagementSystem.service.DashboardService;
import com.companyA.backend.InventoryManagementSystem.model.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/dashboard")
@CrossOrigin
@AllArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/graphs")
    public GraphsDataDTO graphsDataHandle() {
        return dashboardService.getGraphsData();
    }
}

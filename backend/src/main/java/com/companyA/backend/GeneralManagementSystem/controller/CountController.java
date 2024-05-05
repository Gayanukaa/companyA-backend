package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.DTO.CountDTO;
import com.companyA.backend.GeneralManagementSystem.service.CustomerService;
import com.companyA.backend.GeneralManagementSystem.service.DashboardService;
import com.companyA.backend.GeneralManagementSystem.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin
public class CountController {
    private final CustomerService customerService;
    private final ManagerService managerService;
    private final DashboardService dashboardService;

    @GetMapping("/count")
    public ResponseEntity<List<CountDTO>>getCounts(){
        List<CountDTO> countDTOS=new ArrayList<>();
        long customerCount = customerService.countCustomers();
        long managerCount= managerService.countManagers();
        long inventoryItemCount= dashboardService.countInventoryItems();
        long vehicleCount= dashboardService.countVehicles();
        countDTOS.add(new CountDTO(customerCount,managerCount,inventoryItemCount,vehicleCount));


        return ResponseEntity.ok(countDTOS);

    }

}

package com.companyA.backend.GeneralManagementSystem.controller;


import com.companyA.backend.GeneralManagementSystem.model.Inventory;
import com.companyA.backend.GeneralManagementSystem.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
@CrossOrigin
public class InventoryGMController {

    private InventoryService inventoryService;

    @PostMapping("/createinventory")
    public String createManagerHandle(@RequestBody Inventory inventory) {
        return InventoryService.createInventory(inventory);

    }

    @GetMapping("/findById")
    public Optional<Inventory> findInventoryById(@RequestParam("id") String id){
        return InventoryService.findInventoryById(id);
    }

    @PostMapping("/updateById")
    public String updateInventory(@RequestBody Inventory inventory){
        return InventoryService.updateInventoryById(inventory);
    }


}

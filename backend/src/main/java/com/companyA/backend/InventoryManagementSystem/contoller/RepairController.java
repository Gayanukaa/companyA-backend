package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Repair;
import com.companyA.backend.InventoryManagementSystem.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @PostMapping("/sendRepair")
    public ResponseEntity<String> sendForRepairs(@RequestBody Repair repair) {
        String sup =  repairService.sendForRepairs(repair);
        return ResponseEntity.status(HttpStatus.OK).body(sup);
    }


    @GetMapping("/getRepairDetails")

    public ResponseEntity<Object> getRepairDetails(@RequestBody List<String> ids) {
        try {
            List<Repair> repairs = repairService.repairDetails(ids);
            return ResponseEntity.ok(repairs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product id");
        }
    }

    @DeleteMapping("/deleteRepair/{id}")
    public ResponseEntity<String> deleteRepairById(@PathVariable String id) {
        try {
            repairService.deleteRepairById(id);
            return ResponseEntity.ok("Item deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

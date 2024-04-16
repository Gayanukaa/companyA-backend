package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Repair;
import com.companyA.backend.InventoryManagementSystem.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    //Give ids of the items with stateOfProduct = "DAMAGED"

    //Ok
    @GetMapping("/getDamagedItems")
    public ResponseEntity<List<String>> getIdsOfDamagedProducts() {
        List<String> damagedProductIds = repairService.getIdsOfDamagedProducts();
        return ResponseEntity.ok(damagedProductIds);
    }


    //StateOfProduct will be updated as "UNDER_REPAIR"("Inventory" collection)
    //Send the details of relevant items to the "Repair" collection
    @GetMapping("/sendForRepairs")
    public ResponseEntity<String> sendItemsForRepair(@RequestBody List<String> itemIds) {
        repairService.sendItemsForRepair(itemIds);
        return ResponseEntity.ok("Items sent for repairs successfully.");
    }


    @GetMapping("/checkRepairApi")
    public ResponseEntity<Object> getRepairDetails(@RequestBody List<String> ids) {
        try {
            List<Repair> repairs = repairService.repairDetails(ids);
            return ResponseEntity.ok(repairs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product id");
        }
    }

    //Can directly delete an item to repair collection
    //And also this method update the inventory back to "DAMAGED"
    //Commented out due to complexity

    /*@DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteRepairById(@PathVariable String id) {
        try {
            repairService.deleteRepairById(id);
            return ResponseEntity.ok("Item deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }*/


    //You should provide a list of repair Ids
    @GetMapping("/itemsRepairDone")
    public ResponseEntity<String> updateRepairedItems(@RequestBody List<String> itemIds) {
        repairService.updateRepairedItems(itemIds);
        return ResponseEntity.ok("Items has been repaired");
    }
}
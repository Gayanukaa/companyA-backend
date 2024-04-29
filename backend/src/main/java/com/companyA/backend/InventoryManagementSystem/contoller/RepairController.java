package com.companyA.backend.InventoryManagementSystem.contoller;

import com.companyA.backend.InventoryManagementSystem.model.Repair;
import com.companyA.backend.InventoryManagementSystem.model.Shipment;
import com.companyA.backend.InventoryManagementSystem.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repair")
@CrossOrigin
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    //Give ids of the items with stateOfProduct = "DAMAGED"

    //Ok
    @GetMapping("/getDamagedItems")
    public ResponseEntity<List<String>> getIdsOfDamagedProducts() {
        List<String> damagedProductIds = repairService.getIdsOfDamagedProducts();
        return ResponseEntity.ok(damagedProductIds);
    }


    //StateOfProduct will be updated as "UNDER_REPAIR"("Inventory" collection)
    //Send the details of relevant items to the "Repair" collection
    @GetMapping("/sendForRepairs/{itemId}")
    public ResponseEntity<String> sendItemsForRepair(@PathVariable String itemId) {
        try {
            repairService.sendItemsForRepair(itemId);
            return ResponseEntity.ok("Item sent for repairs successfully.");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong product ID.Selected item should be DAMAGED to proceed.");
        }
    }

    @GetMapping("/checkRepairApi")
    public ResponseEntity<Object> getRepairDetails(@RequestBody List<String> ids) {
        try {
            List<Repair> repairs = repairService.repairDetails(ids);
            return ResponseEntity.ok(repairs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong product ID.");
        }
    }

    //Retrieves all item details which are in under repair
    @GetMapping("/getAllRepairs")
    public ResponseEntity<List<Repair>> getAllRepairs() {
        List<Repair> repairs = repairService.getAllRepairs();
        if (repairs == null || repairs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(repairs, HttpStatus.OK);
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
    @GetMapping("/itemsRepairDone/{itemId}")
    public ResponseEntity<String> updateRepairedItems(@PathVariable String itemId) {
        try {
            repairService.updateRepairedItems(itemId);
            return ResponseEntity.ok("Item has been repaired.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong repair ID.");
        }
    }
}
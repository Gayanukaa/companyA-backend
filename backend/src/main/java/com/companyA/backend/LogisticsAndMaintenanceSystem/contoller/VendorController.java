package com.companyA.backend.LogisticsAndMaintenanceSystem.contoller;


import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Machinery;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vendor;
import com.companyA.backend.LogisticsAndMaintenanceSystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendor")
@CrossOrigin
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping("/getVendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return new ResponseEntity<>(vendorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/vendorById/{vendorId}")
    public ResponseEntity<Optional<Vendor>> getVendorById(@PathVariable String vendorId) {
        return new ResponseEntity<>(vendorService.findVendorById(vendorId), HttpStatus.OK);
    }

    @PostMapping("/addVendor")
    public ResponseEntity<Map<String,String>> setVendor(@RequestBody Vendor vendor) {
        vendorService.add(vendor);
        Map<String,String> response = new HashMap<>();
        response.put("status", "Vendor is successfully added ");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateVendor/{id}")
    public Vendor updateVendor(@PathVariable String id, @RequestBody Map<String,Object> fields) {
        return vendorService.updateVendor(id,fields);
    }

    @DeleteMapping("/deleteVendor/{vendorId}")
    public ResponseEntity<Void> deleteVendor(@PathVariable String vendorId) {
        vendorService.deleteVendor(vendorId);
        return ResponseEntity.noContent().build();
    }
}








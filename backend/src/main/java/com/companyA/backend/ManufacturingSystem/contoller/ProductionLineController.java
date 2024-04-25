package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.ProductionData;
import com.companyA.backend.ManufacturingSystem.model.Employee;
import com.companyA.backend.ManufacturingSystem.service.ProductionDataService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// Controller class for handling ProductionLine requests
@RestController
@RequestMapping("/api/production/line")
public class ProductionLineController {
    @Autowired
    private ProductionDataService productionDataService;

    // Endpoint for retrieving production line report
    @GetMapping("/productionLineData")
    public ResponseEntity<Object> getProductionLineReport(@RequestParam("lineId") String lineId) {

        // Get all production data
        List<ProductionData> productionDataList = productionDataService.getAllProductionData();

        // Filter production data by lineId
        List<ProductionData> reports = productionDataList.stream()
                .filter(data -> data.getLineId().equals(lineId)) // Filter by lineId
                .collect(Collectors.toList());

        if (!reports.isEmpty()) {
            // Return production data if found
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No data found for the production line.", HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint for creating a new production line
    @PostMapping("/create")
    public ResponseEntity<Object> createProductionLine(@RequestBody ProductionData productionData) {
        // Create a new production line
        ProductionData newProductionData = productionDataService.createProductionData(productionData);
        return new ResponseEntity<>(newProductionData, HttpStatus.CREATED);
    }
}
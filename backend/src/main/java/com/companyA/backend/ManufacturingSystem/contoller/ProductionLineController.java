package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.ProductionData;
import com.companyA.backend.ManufacturingSystem.service.ProductionDataService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/production/line")
public class ProductionLineController {
    @Autowired
    private ProductionDataService productionDataService;

    @GetMapping("/report")
    public ResponseEntity<Object> getProductionLineReport(@RequestParam("id") ObjectId id,
                                                          @RequestParam(value = "auth_token", required = false) String authToken) {
        // Authenticate the request using the authToken
        // For simplicity, we'll skip the authentication part in this example
        if (authToken == null || !authToken.equals("abc123")) {
            return new ResponseEntity<>("Invalid auth_token", HttpStatus.UNAUTHORIZED);
        }

        List<ProductionData> productionDataList = productionDataService.getAllProductionData();

        if (!productionDataList.isEmpty()) {
            List<ProductionData> reports = productionDataList.stream()
                    .filter(data -> data.getId().equals(id)) // Filter by _id
                    .collect(Collectors.toList());

            if (!reports.isEmpty()) {
                return new ResponseEntity<>(reports, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No data found for the production line.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid production line ID or insufficient permissions.", HttpStatus.BAD_REQUEST);
        }
    }
}
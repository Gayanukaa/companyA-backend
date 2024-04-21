package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.ProductionData;
import com.companyA.backend.ManufacturingSystem.service.ProductionDataService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

// Controller class for handling ProductionLine requests
@RestController
@RequestMapping("/api/production/line")

public class ProductionLineController {
    private ProductionDataService productionDataService;

    // Endpoint for retrieving production line report
    @GetMapping("/report")
    public ResponseEntity<Object> getProductionLineReport(@RequestParam("lineId") String lineId,
                                                          @RequestParam(value = "auth_token", required = false) String authToken) {
        // Authenticate the request using the authToken
        // For simplicity, we'll skip the authentication part in this example
        if (authToken == null || !authToken.equals("abc123")) {
            return new ResponseEntity<>("Invalid auth_token", HttpStatus.UNAUTHORIZED);
        }

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
}
package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.MaterialRequest;
import com.companyA.backend.ManufacturingSystem.service.MaterialRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class MaterialRequestController {

    @Autowired
    private MaterialRequestService materialRequestService;

    // Endpoint to update material request quantity
    @PutMapping("/updateMaterialRequest")
    public String updateMaterialRequest(@RequestBody MaterialRequest materialRequest) {
        return materialRequestService.updateMaterialRequest(materialRequest);
    }
}
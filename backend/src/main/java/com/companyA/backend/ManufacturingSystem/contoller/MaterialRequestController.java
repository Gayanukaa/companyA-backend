package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.MaterialRequest;
import com.companyA.backend.ManufacturingSystem.service.MaterialRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin
public class MaterialRequestController {

    @Autowired
    private MaterialRequestService materialRequestService;

    // Endpoint to update material request quantity
    @PutMapping("/updateMaterialRequest")
    public String updateMaterialRequest(@RequestBody MaterialRequest materialRequest) {
        return materialRequestService.updateMaterialRequest(materialRequest);
    }
}
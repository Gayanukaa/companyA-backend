package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.ProductionData;
import com.companyA.backend.ManufacturingSystem.repository.ProductionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductionDataService {
    @Autowired
    private ProductionDataRepository productionDataRepository;

    public List<ProductionData> getAllProductionData() {
        return productionDataRepository.findAll();
    }
}

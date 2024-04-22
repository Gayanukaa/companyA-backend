package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.Product;

import com.companyA.backend.ManufacturingSystem.repository.ManufacturingProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class ManufacturingProductService {
    private final ManufacturingProductRepository manufacturingProductRepository;

    public ManufacturingProductService(ManufacturingProductRepository manufacturingProductRepository) {
        this.manufacturingProductRepository = manufacturingProductRepository;
    }

    // Method to retrieve all products
    public List<Product> getAllProducts() {         // Call the product repository to fetch all products
        return manufacturingProductRepository.findAll();
    }

}

package com.companyA.backend.ManufacturingSystem.service;

import com.companyA.backend.ManufacturingSystem.model.Product;
import com.companyA.backend.ManufacturingSystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    // Method to retrieve all products
    public List<Product> getAllProducts() {         // Call the product repository to fetch all products
        return productRepository.findAll();
    }

}

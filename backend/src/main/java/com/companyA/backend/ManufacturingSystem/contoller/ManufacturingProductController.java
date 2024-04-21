package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.Product;
import com.companyA.backend.ManufacturingSystem.service.ManufacturingProductService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")

public class ManufacturingProductController {
    private ManufacturingProductService manufacturingProductService;

    // Endpoint to retrieve all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = manufacturingProductService.getAllProducts();       // Call the ProductService to get all products
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}

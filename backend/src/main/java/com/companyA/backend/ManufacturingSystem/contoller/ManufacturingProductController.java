package com.companyA.backend.ManufacturingSystem.contoller;

import com.companyA.backend.ManufacturingSystem.model.Product;
import com.companyA.backend.ManufacturingSystem.service.ManufacturingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ManufacturingProductController {
    @Autowired
    private ManufacturingProductService manufacturingProductService;

    // Endpoint to retrieve all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = manufacturingProductService.getAllProducts();       // Call the ProductService to get all products
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}

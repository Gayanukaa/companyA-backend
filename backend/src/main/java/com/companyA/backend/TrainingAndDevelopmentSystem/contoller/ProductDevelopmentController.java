package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.ProductDevelopment;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.ProductDevelopmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductDevelopmentController {
    @Autowired
    private ProductDevelopmentRepository productDevelopmentRepository;

    @GetMapping("/api/tms/product-developments")
    List<ProductDevelopment> getAllProductDevelopments(){
        return productDevelopmentRepository.findAll();
    }

    @PostMapping("/api/tms/product-development")
    ProductDevelopment newProductDevelopment(@RequestBody ProductDevelopment newProductDevelopment){
        return productDevelopmentRepository.save(newProductDevelopment);
    }

    @GetMapping("/api/tms/product-development/{id}")
    ProductDevelopment getProgress(@PathVariable String id) {
        return productDevelopmentRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
}

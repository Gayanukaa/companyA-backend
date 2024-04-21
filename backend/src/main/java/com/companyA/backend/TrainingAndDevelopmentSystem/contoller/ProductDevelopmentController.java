package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.ProductDevelopment;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.ProductDevelopmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/api/tms/product-development/{id}")
    public ResponseEntity<ProductDevelopment> updateStage(@PathVariable String id, @RequestBody ProductDevelopment updatedProductDevelopment) {
        Optional<ProductDevelopment> existingProductDevelopmentOptional = productDevelopmentRepository.findById(id);

        if (existingProductDevelopmentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProductDevelopment existingProductDevelopment = existingProductDevelopmentOptional.get();

        // Update stage values
        existingProductDevelopment.setStageOne(updatedProductDevelopment.isStageOne());
        existingProductDevelopment.setStageTwo(updatedProductDevelopment.isStageTwo());
        existingProductDevelopment.setStageThree(updatedProductDevelopment.isStageThree());

        ProductDevelopment savedProductDevelopment = productDevelopmentRepository.save(existingProductDevelopment);

        return ResponseEntity.ok(savedProductDevelopment);
    }
}

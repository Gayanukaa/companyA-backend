package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.OverseasExperience;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.Products;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductsController {
    @Autowired
    private ProductsRepository  productsRepository;

    @PostMapping("/api/tms/products")
    Products newProduct(@RequestBody Products newProduct){
        return productsRepository.save(newProduct);
    }

    @GetMapping("/api/tms/products")
    List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    @GetMapping("/api/tms/products/{id}")
    Products getCourseById(@PathVariable String id){
        return productsRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }


}

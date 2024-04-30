package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.OverseasExperience;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.Products;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
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
    Products getProductById(@PathVariable String id){
        return productsRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }


    //productDetails method implements here
    @GetMapping("/api/tms/product/{id}/details")
    public ResponseEntity<String> getProductDetailsById(@PathVariable String id) {
        Products product = productsRepository.findById(id).orElse(null);
        if (product != null) {
            return new ResponseEntity<>(product.getProductDetails(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }


}

package com.companyA.backend.SalesSystem.contoller;
import com.companyA.backend.SalesSystem.model.*;
import com.companyA.backend.SalesSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>>getAllProducts(){
        return new ResponseEntity<List<Product>>(productService.allProducts(), HttpStatus.OK);
    }

    @PostMapping("/validateStock")
    public ResponseEntity<StockValidationResponse> validateStock1(@RequestBody SingleItemRequest request) { // Customize request class name
        String itemId = request.getItemId();
        int quantity = request.getQuantity();
        boolean hasStock = productService.hasSufficientStock(itemId, quantity);
        if (hasStock) {
            return ResponseEntity.ok(new StockValidationResponse(true, "Stock is sufficient"));
        } else {
            return ResponseEntity.badRequest()
                    .body(new StockValidationResponse(false, "Insufficient stock for item " + itemId));
        }
    }

    @PostMapping("/validateStock-Multiple")
    public ResponseEntity<List<StockValidation>> validateStock(@RequestBody List<CartItem> cartItems) {
        List<StockValidation> validationResults = new ArrayList<>();
        System.out.println("In the product controller 1");
        for (CartItem item : cartItems) {
            double subTotal = 0;
            boolean hasStock = productService.hasSufficientStock(item.getItemId(), item.getQuantity());
            int stock = productService.getStock(item.getItemId());

            System.out.println("In the product controller");
            if (hasStock){
                System.out.println("In the if section product controller");
                System.out.println(item.getQuantity());
                System.out.println(item.getUnitPrice());
                subTotal =  item.getQuantity()*item.getUnitPrice();
            }
            validationResults.add(new StockValidation(item.getItemId(), hasStock, stock, subTotal ));
        }
        return ResponseEntity.ok(validationResults);
    }

}

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
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>>getAllProduclts(){
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

    @PostMapping("/validateStock-Muliple")
    public ResponseEntity<List<StockValidation>> validateStock(@RequestBody List<CartItem> cartItems) {
        List<StockValidation> validationResults = new ArrayList<>();
        double subTotal = 0;
        for (CartItem item : cartItems) {
            boolean hasStock = productService.hasSufficientStock(item.getItemId(), item.getQuantity());
            validationResults.add(new StockValidation(item.getItemId(), hasStock));
            if (hasStock){
                System.out.println("In the if clasue");
                System.out.println(item.getQuantity());
                System.out.println(item.getUnitPrice());
                subTotal = subTotal + item.getQuantity()*item.getUnitPrice();
            }
        }
        String totalPriceString = String.valueOf(subTotal);
        System.out.println(totalPriceString);
        validationResults.add(new StockValidation(totalPriceString, true));
        return ResponseEntity.ok(validationResults);
    }

}

package com.companyA.backend.SalesSystem.contoller;
import com.companyA.backend.SalesSystem.model.CartItem;
import com.companyA.backend.SalesSystem.model.Existing;
import com.companyA.backend.SalesSystem.model.SingleItemRequest;
import com.companyA.backend.SalesSystem.model.StockValidationResponse;
import com.companyA.backend.SalesSystem.model.StockValidation;
import com.companyA.backend.SalesSystem.service.ExistingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/existingProducts")
public class ExistingProductController {
    @Autowired
    private ExistingProductService existingProductService;
    @GetMapping
    public ResponseEntity<List<Existing>> getAllExistingProducts(){
        System.out.println("In thr java all product controller");
        return new ResponseEntity<List<Existing>>(existingProductService.allExistingProducts(), HttpStatus.OK);
    }

    @PostMapping("/validateStock")
    public ResponseEntity<StockValidationResponse> validateStock1(@RequestBody SingleItemRequest request) { // Customize request class name

        System.out.println("In thr java controller");
        String itemId = request.getItemId();
        int quantity = request.getQuantity();
        boolean hasStock = existingProductService.hasSufficientStock(itemId, quantity);
        if (hasStock) {
            return ResponseEntity.ok(new StockValidationResponse(true, "Stock is sufficient"));
        } else {
            return ResponseEntity.badRequest()
                    .body(new StockValidationResponse(false, "Insufficient stock for item " + itemId));
        }
    }

    @PostMapping("/validateStock-Muliple")
    public ResponseEntity<List<StockValidation>> validateStock(@RequestBody List<CartItem> cartItem) {
        List<StockValidation> validationResults = new ArrayList<>();

        for (CartItem itemBOM : cartItem) {
            double subTotal = 0;
            boolean hasStock = existingProductService.hasSufficientStock(itemBOM.getItemId(), itemBOM.getQuantity());
            int stock = existingProductService.getStock(itemBOM.getItemId());
            if (hasStock){
                System.out.println("In the if clasue");
                System.out.println(itemBOM.getQuantity());
                System.out.println(itemBOM.getUnitPrice());
                subTotal = itemBOM.getQuantity()*itemBOM.getUnitPrice();
            }
            validationResults.add(new StockValidation(itemBOM.getItemId(), hasStock, stock, subTotal));
        }
        return ResponseEntity.ok(validationResults);
    }

    @PostMapping("/addProduct") // Handles POST requests
    public ResponseEntity<Existing> addProduct(@RequestBody Existing product) {
        Existing newProduct = existingProductService.addProduct(product);
        return ResponseEntity.ok(newProduct); // Return created product with status code 200
    }

    @DeleteMapping("/delete/{id}") // Handles DELETE requests with product ID path variable
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        existingProductService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content on success
    }


}


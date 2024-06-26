package com.companyA.backend.SalesSystem.contoller;
import com.companyA.backend.SalesSystem.model.*;
import com.companyA.backend.SalesSystem.service.ExistingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/existingProducts")
@CrossOrigin
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

    @PostMapping("/validateStock-Multiple")
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
        String id = product.getId();
        boolean isExist = existingProductService.findbyID(id);
        if(!isExist){
            System.out.println(("Adding to the database"));
            Existing newProduct = existingProductService.addProduct(product);
            return ResponseEntity.ok(newProduct); // Return created product with status code 200
        }else{
            Existing newProduct = new Existing();
            String error = "item already exists";
            // Return 404 Not Found with the error message
            return ResponseEntity.badRequest()
                    .body(new Existing());
        }

    }

    @DeleteMapping("/delete/{id}") // Handles DELETE requests with product ID path variable
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        boolean isExist = existingProductService.findbyID(id);
        if (isExist){
            existingProductService.deleteProduct(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on success
        }
        else {
            String error = "Item not found";
            // Return 404 Not Found with the error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    @PostMapping("/updateTable")
    public ResponseEntity<String> updateDatabase(@RequestBody List<UpdateData> cartItem){

        for (UpdateData itemPurchased : cartItem){
            existingProductService.updateDatabase(itemPurchased);
        }
        return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
    }

    @PostMapping("/updateStock")
    public ResponseEntity<String> updateStock(@RequestBody UpdateExistingProductData cartItem){
        String id = cartItem.getItemId();
        boolean isExist = existingProductService.findbyID(id);
        if (isExist){
            boolean result = existingProductService.updateStock(cartItem);
            if (!result){
                return new ResponseEntity<String>("Can't update process stopped", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
        }
        else {
            String error = "Item not found";
            // Return 404 Not Found with the error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}


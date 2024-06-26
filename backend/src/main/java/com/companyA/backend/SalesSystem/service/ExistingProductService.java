package com.companyA.backend.SalesSystem.service;
import com.companyA.backend.SalesSystem.model.CartItem;
import com.companyA.backend.SalesSystem.model.Existing;
import com.companyA.backend.SalesSystem.model.UpdateData;
import com.companyA.backend.SalesSystem.model.UpdateExistingProductData;
import com.companyA.backend.SalesSystem.repository.ExistingProductRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExistingProductService {

    @Autowired
    private ExistingProductRepository existingProductRepository;
    public List<Existing> allExistingProducts() {
        return existingProductRepository.findAll();
    }


    public boolean hasSufficientStock(String itemId, int quantity) {
        System.out.println("In the service class");
        Existing item = existingProductRepository.findById(itemId).orElse(null);
        if (item == null) {
            // Handle error if item not found
            return false;
        }
        return item.getQuantity() >= quantity;
    }

    public int getStock(String itemId){
        Existing item = existingProductRepository.findById(itemId).orElse(null);
        if (item == null) {
            // Handle error if item not found
            return 0;
        }
        return item.getQuantity();
    }
    public Existing addProduct(Existing product) {
        return existingProductRepository.save(product); // Save to MongoDB
    }

    public void deleteProduct(String id) {
        existingProductRepository.deleteById(id);
    }

    public boolean findbyID(String itemID){
        Existing item = existingProductRepository.findById(itemID).orElse(null);
        if (item != null){
            System.out.println("Not null add product");
            return true;
        }
        return false;
    }

    public void updateDatabase(UpdateData record){
        String itemID = record.getItemId();
        int itemQuantity = record.getQuantity();
        Existing item = existingProductRepository.findById(itemID).orElse(null);
        int remaining = item.getQuantity() - itemQuantity;
        System.out.println("r "+remaining);
        if (remaining <0){
            remaining = 0;
        }
        item.setQuantity(remaining);
        existingProductRepository.save(item);

    }

    public boolean updateStock(UpdateExistingProductData record){
        String itemID = record.getItemId();
        int itemQuantity = record.getQuantity();
        double itemPrice = record.getPrice();
        String warehouse = record.getWarehouseId();
        Existing item = existingProductRepository.findById(itemID).orElse(null);
        if (item == null){
            return false;
        }
        int remaining = item.getQuantity() + itemQuantity;
        item.setQuantity(remaining);
        item.setWarehouseId(warehouse);
        item.setPrice(itemPrice);
        existingProductRepository.save(item);
        return true;
    }

}


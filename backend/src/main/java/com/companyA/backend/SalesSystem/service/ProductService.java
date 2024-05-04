package com.companyA.backend.SalesSystem.service;

import com.companyA.backend.SalesSystem.model.CartItem;
import com.companyA.backend.SalesSystem.model.Existing;
import com.companyA.backend.SalesSystem.model.Product;
import com.companyA.backend.SalesSystem.model.UpdateData;
import com.companyA.backend.SalesSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public boolean hasSufficientStock(String itemId, int quantity) {
        System.out.println("In the service class");
        Product item = productRepository.findById(itemId).orElse(null);
        if (item == null) {
            // Handle error if item not found
            return false;
        }
        return item.getQuantity() >= quantity;
    }

    public int getStock(String itemId) {
        Product item = productRepository.findById(itemId).orElse(null);
        if (item == null) {
            // Handle error if item not found
            return 0;
        }
        return item.getQuantity();
    }

    public void updateDatabase(UpdateData record) {
        String itemID = record.getItemId();
        int itemQuantity = record.getQuantity();
        Product item = productRepository.findById(itemID).orElse(null);
        item.setQuantity(item.getQuantity() - itemQuantity);
        productRepository.save(item);
    }

}

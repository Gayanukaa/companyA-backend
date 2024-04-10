package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.InventoryType;
import com.companyA.backend.InventoryManagementSystem.model.StateOfProduct;
import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import com.companyA.backend.InventoryManagementSystem.model.Warehouse;
import com.companyA.backend.InventoryManagementSystem.repository.StocksRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StocksService {


    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public Stocks addStocks(String id, String warehouseId, String name, String quantity, String weight, String size, String reorderQuantity, String stateOfProduct, String inventoryType, String price) {
        int quantityAdd = Integer.parseInt(quantity);
        int weightAdd = Integer.parseInt(weight);
        int sizeAdd = Integer.parseInt(size);
        int reorderQuantityAdd = Integer.parseInt(reorderQuantity);
        float priceAdd = Float.parseFloat(price);
        StateOfProduct stateOfProductAdd = StateOfProduct.valueOf(stateOfProduct);
        InventoryType inventoryTypeAdd = InventoryType.valueOf(inventoryType);

        /*mongoTemplate.update(Warehouse.class)
                .matching(Criteria.where("warehouseId").is(newStock.getWarehouseId()))
                .apply(new Update().push("inventoryList").value(newStock.getId()))
                .first();*/

        return stocksRepository.insert(new Stocks(id,
                warehouseId,
                name,
                quantityAdd,
                weightAdd,
                sizeAdd,
                reorderQuantityAdd,
                stateOfProductAdd,
                inventoryTypeAdd,
                priceAdd
        ));
    }

    public List<Stocks> allStocks() {
        return stocksRepository.findAll();
    }
}

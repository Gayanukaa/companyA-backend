package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.*;
import com.companyA.backend.InventoryManagementSystem.repository.SuppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliesService {

    @Autowired
    private SuppliesRepository suppliesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Supplies> allSupplies() {
        return suppliesRepository.findAll();
    }

    public Supplies addSupplies(Supplies supplies) {
        if(!suppliesRepository.findAll().isEmpty()) {
            String lastId = suppliesRepository.findAll().get(suppliesRepository.findAll().size()-1).getId();
            int id = Integer.parseInt(lastId.substring(1));
            id++;
            supplies.setId("I"+String.format("%04d", id));
        }
        else {
            supplies.setId("I0001");
        }
        Query query = new Query(Criteria.where("warehouseId").is(supplies.getWarehouseId()).and("inventoryList.0").is(null));
        boolean hasNullAtFirstIndex = mongoTemplate.exists(query, Warehouse.class);

        if (hasNullAtFirstIndex) {
            Update update = new Update().set("inventoryList.0", supplies.getId());
            mongoTemplate.updateFirst(query, update, Warehouse.class);
        } else {
            mongoTemplate.update(Warehouse.class)
                    .matching(Criteria.where("warehouseId").is(supplies.getWarehouseId()))
                    .apply(new Update().push("inventoryList", supplies.getId())).first();
        }

        return suppliesRepository.save(supplies);
    }

    public String deleteSupplies(String suppliesId) {
        Supplies supplies = suppliesRepository.findById(suppliesId).orElse(null);
        if (supplies == null) {
            return HttpStatus.NOT_FOUND.toString();
        }
        mongoTemplate.update(Warehouse.class)
                .matching(Criteria.where("warehouseId").is(supplies.getWarehouseId())).apply(new Update().pull("inventoryList", supplies.getId())).first();
        suppliesRepository.deleteById(suppliesId);
        return HttpStatus.OK.toString();
    }

    public Supplies getSuppliesById(String id) {
        return suppliesRepository.findById(id).orElse(null);
    }

    public boolean existsById(String id) {
        return suppliesRepository.existsById(id);
    }

    public void updateSupplies(Supplies supplies) {
        suppliesRepository.save(supplies);
    }

    public void updateSuppliesByAttribute(Supplies supplies, String attribute, String value) {
        switch (attribute) {
            case "name":
                supplies.setName(value);
                break;
            case "quantity":
                supplies.setQuantity(Integer.parseInt(value));
                break;
            case "weight":
                supplies.setWeight(Integer.parseInt(value));
                break;
            case "size":
                supplies.setSize(Integer.parseInt(value));
                break;
            case "reorderQuantity":
                supplies.setReorderQuantity(Integer.parseInt(value));
                break;
            case "stateOfProduct":
                supplies.setStateOfProduct(StateOfProduct.valueOf(value));
                break;
            case "inventoryType":
                supplies.setInventoryType(InventoryType.valueOf(value));
                break;
        }
        suppliesRepository.save(supplies);
    }
}
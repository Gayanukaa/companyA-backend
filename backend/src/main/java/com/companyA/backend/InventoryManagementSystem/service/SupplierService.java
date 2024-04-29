package com.companyA.backend.InventoryManagementSystem.service;

import com.companyA.backend.InventoryManagementSystem.model.InventoryManager;
import com.companyA.backend.InventoryManagementSystem.model.Suppliers;
import com.companyA.backend.InventoryManagementSystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, MongoTemplate mongoTemplate) {
        this.supplierRepository = supplierRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Suppliers getSupplierById(String id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
    }

    public List<Suppliers> supplierDetails(){
        return supplierRepository.findAll();
    }

    public Suppliers registerSupplier(Suppliers supplier) {
        if(!supplierRepository.findAll().isEmpty()) {
            String lastId = supplierRepository.findAll().get(supplierRepository.findAll().size()-1).getSupplierId();
            int id = Integer.parseInt(lastId.substring(1));
            id++;
            supplier.setSupplierId("S"+String.format("%04d", id));
        }
        else {
            supplier.setSupplierId("S0001");
        }

        Query query = new Query(Criteria.where("managerId").is(supplier.getManagerId()).and("listOfSuppliers.0").is(null));
        boolean hasNullAtFirstIndex = mongoTemplate.exists(query, InventoryManager.class);

        if (hasNullAtFirstIndex) {
            Update update = new Update().set("listOfSuppliers.0", supplier.getSupplierId());
            mongoTemplate.updateFirst(query, update, InventoryManager.class);
        } else {
            mongoTemplate.update(InventoryManager.class)
                    .matching(Criteria.where("managerId").is(supplier.getManagerId()))
                    .apply(new Update().push("listOfSuppliers", supplier.getSupplierId())).first();
        }

        return supplierRepository.save(supplier);
    }

    public void deleteSupplierById(String id) {
        // Check if the repair exists
        if (supplierRepository.existsById(id)) {
            // If the repair exists, delete it
            supplierRepository.deleteById(id);
        } else {
            // If the repair does not exist, throw an exception
            throw new IllegalArgumentException("Repair with ID " + id + " does not exist.");
        }
    }

}
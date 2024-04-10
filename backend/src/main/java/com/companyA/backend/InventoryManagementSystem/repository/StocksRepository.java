package com.companyA.backend.InventoryManagementSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StocksRepository extends MongoRepository<Stocks, ObjectId>{

}


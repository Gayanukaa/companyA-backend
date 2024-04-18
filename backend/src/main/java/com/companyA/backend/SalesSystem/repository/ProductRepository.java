package com.companyA.backend.SalesSystem.repository;

import com.companyA.backend.SalesSystem.model.Existing;
import com.companyA.backend.SalesSystem.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    Optional<Product> findById(String ObjectId);

    void deleteById(String id);
}

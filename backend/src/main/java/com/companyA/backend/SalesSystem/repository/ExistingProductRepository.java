package com.companyA.backend.SalesSystem.repository;

import com.companyA.backend.SalesSystem.model.Existing;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExistingProductRepository extends MongoRepository<Existing, ObjectId> {

    Optional<Existing> findById(String ObjectId);

    void deleteById(String id);

    //Existing updateProduct(String id, String columnName, Object newValue);

    //Optional<Existing> hasSufficientStock(String ObjectId, int quantity);
}

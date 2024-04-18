package com.companyA.backend.SalesSystem.repository;
import com.companyA.backend.SalesSystem.model.CustomerData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface SalesTableRepository extends MongoRepository<CustomerData, ObjectId> {

    Optional<CustomerData> findById(ObjectId ObjectId);

    CustomerData  save(CustomerData customer);
}




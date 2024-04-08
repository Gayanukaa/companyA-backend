package com.companyA.backend.GeneralManagementSystem.repository;

import com.companyA.backend.GeneralManagementSystem.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository <Customer, String> {
    boolean existsByEmail(String email);
}

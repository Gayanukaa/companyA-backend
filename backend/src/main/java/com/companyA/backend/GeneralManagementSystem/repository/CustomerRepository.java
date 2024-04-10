package com.companyA.backend.GeneralManagementSystem.repository;

import com.companyA.backend.GeneralManagementSystem.model.Customer;
import com.companyA.backend.GeneralManagementSystem.model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends UserRepository <Customer> {
    Customer findByEmail(String email);
}

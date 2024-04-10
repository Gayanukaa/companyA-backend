package com.companyA.backend.GeneralManagementSystem.repository;

import com.companyA.backend.GeneralManagementSystem.model.Customer;
import com.companyA.backend.GeneralManagementSystem.model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagerRepository extends UserRepository <Manager> {

}

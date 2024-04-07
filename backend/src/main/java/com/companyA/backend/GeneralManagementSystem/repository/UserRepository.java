package com.companyA.backend.GeneralManagementSystem.repository;

import com.companyA.backend.GeneralManagementSystem.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository <User, String> {
}

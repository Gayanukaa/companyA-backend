package com.companyA.backend.GeneralManagementSystem.repository;

import com.companyA.backend.GeneralManagementSystem.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository <T extends User> extends MongoRepository <T, String> {

    T findByEmail(String email);
}

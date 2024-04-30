package com.companyA.backend.CustomerOrderSystem.service;
import com.companyA.backend.CustomerOrderSystem.model.UserDetails;
import com.companyA.backend.CustomerOrderSystem.repository.UserDetailsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public Optional<UserDetails> getUserDetails(ObjectId user_Id) {
        return userDetailsRepository.findById(user_Id);
    }
}
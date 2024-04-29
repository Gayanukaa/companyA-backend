package com.companyA.backend.CustomerOrderSystem.service;
import com.companyA.backend.CustomerOrderSystem.model.UserDeliveryAddress;
import com.companyA.backend.CustomerOrderSystem.repository.UserDeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeliveryAddressService {

    @Autowired
    private UserDeliveryAddressRepository userDeliveryAddressRepository;

    public void userDeliveryAddress(UserDeliveryAddress userDeliveryAddress) {
        userDeliveryAddressRepository.save(userDeliveryAddress);
    }
}
package com.companyA.backend.CustomerOrderSystem.contoller;
import com.companyA.backend.CustomerOrderSystem.model.UserDeliveryAddress;
import com.companyA.backend.CustomerOrderSystem.service.UserDeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin

@RestController
@RequestMapping("/api/v1/User delivery addresses")
public class UserDeliveryAddressController {

    @Autowired
    private UserDeliveryAddressService userDeliveryAddressService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createUserDeliveryAddress(@RequestBody UserDeliveryAddress userDeliveryAddress) {
        userDeliveryAddressService.userDeliveryAddress(userDeliveryAddress);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Address sent successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
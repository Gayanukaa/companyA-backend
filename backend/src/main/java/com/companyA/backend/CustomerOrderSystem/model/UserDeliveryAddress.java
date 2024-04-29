package com.companyA.backend.CustomerOrderSystem.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User delivery addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeliveryAddress {

    @Id
    private String id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    // Getters and setters
}

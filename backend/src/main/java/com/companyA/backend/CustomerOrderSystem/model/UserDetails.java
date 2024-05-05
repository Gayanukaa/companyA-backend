package com.companyA.backend.CustomerOrderSystem.model;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

@Document(collection = "customers")
@Data
@AllArgsConstructor
@CrossOrigin
public class UserDetails {
    @Id
    private ObjectId _id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String id;
}

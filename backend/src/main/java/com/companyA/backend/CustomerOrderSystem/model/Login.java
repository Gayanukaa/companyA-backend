package com.companyA.backend.CustomerOrderSystem.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @Id
    private ObjectId id;
    private String email;
    private String password;
}

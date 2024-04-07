package com.companyA.backend.GeneralManagementSystem.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @Id
    private String id;
    private String role;
}

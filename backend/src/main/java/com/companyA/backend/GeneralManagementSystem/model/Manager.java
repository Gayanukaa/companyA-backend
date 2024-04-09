package com.companyA.backend.GeneralManagementSystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "manager")
@Getter
@Setter
public class Manager extends User {
    private String role;
}
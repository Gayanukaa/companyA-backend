package com.companyA.backend.InventoryManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Getter
@Setter
@Document(collection = "InventoryManager")
public class InventoryManager {
    @Id@NotBlank (message = "Manager Id cannot be blank")
    private String managerId; //Manager Id
    @NotBlank (message = "Manager name cannot be blank")
    private String managerName; //Manager name
    @NotBlank (message = "Manager email cannot be blank")
    private String managerEmail; //Manager email
    @NotBlank (message = "Manager phone number cannot be blank")
    private String managerPhone; //Manager phone number
    @DocumentReference
    private List<Suppliers> listOfSuppliers; // List to store list of suppliers offered
}

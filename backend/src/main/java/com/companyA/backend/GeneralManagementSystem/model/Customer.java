package com.companyA.backend.GeneralManagementSystem.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Document(collection = "customers")
public class Customer extends User{

}

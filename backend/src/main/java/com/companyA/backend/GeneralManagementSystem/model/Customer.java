package com.companyA.backend.GeneralManagementSystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Document(collection = "customers")
@Getter
@Setter
public class Customer extends User{

}

package com.companyA.backend.CustomerOrderSystem.model;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Quotation")
@Data
public class GetQuotation {
    @Id
    private ObjectId id;
    private Object components;
    //private int quantity;
    //private double price;

}


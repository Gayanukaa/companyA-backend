package com.companyA.backend.CustomerOrderSystem.model;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Sales record")
@Data
public class SalesRecord {
    @Id
    private ObjectId id;
    private Object orders;
}

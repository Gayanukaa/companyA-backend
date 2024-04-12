package com.companyA.backend.CustomerOrderSystem.model;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Quotation") // Girlss.....This annotation specifies that instances of this class will be stored in the MongoDB collection named "Quotation".
@Data //Girlss, @Data annotation automatically generates getter, setter, equals(), hashCode(), and toString() methods for the class members.
public class GetQuotation {
    @Id // This annotation indicates that it is the primary identifier for documents of this class in MongoDB. It will typically map to the _id field in MongoDB.
    private ObjectId id;
    private Object components;
    private int quantity;
    private double price;
}


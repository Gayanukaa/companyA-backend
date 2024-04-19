package com.companyA.backend.SalesSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Sales record")
public class CustomerData {
    @Id
    private ObjectId _id;
    private List<SalesRecord> orders;

    public void addOrder(SalesRecord order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }
}

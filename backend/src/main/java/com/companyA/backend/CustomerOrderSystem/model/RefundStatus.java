package com.companyA.backend.CustomerOrderSystem.model;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Refund status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundStatus {
    @Id
    private ObjectId id;
    private String status;
}


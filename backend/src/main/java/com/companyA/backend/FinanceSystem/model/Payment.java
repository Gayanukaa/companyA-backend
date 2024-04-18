package com.companyA.backend.FinanceSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "paymentConfirmation")
public class Payment {
    private ObjectId id;
    private String type; // "Incoming" or "Outgoing"
    private Double amount;
    private String referenceNumber;
    private String status; // "Pending", "Confirmed", "Failed"
    private LocalDateTime paymentDate;
}


package com.companyA.backend.InventoryManagementSystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Shipments")
public class Shipment {
    @Id
    private String id;
    private String trackingNumber;
    private String senderName;
    private String recipientName;
    private String originAddress;
    private String destinationAddress;
    private String status;

}


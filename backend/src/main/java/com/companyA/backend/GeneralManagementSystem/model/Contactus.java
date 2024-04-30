package com.companyA.backend.GeneralManagementSystem.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "contactus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contactus {
    @Id
    private String id;

    @CreatedDate
    private Date timestamp ;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String subject;

    @NotBlank
    private String message;

    private Integer isRead;
}

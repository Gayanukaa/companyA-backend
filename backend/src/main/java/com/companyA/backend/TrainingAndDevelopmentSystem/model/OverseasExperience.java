package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OverseasExperience")
@Data
public class OverseasExperience extends Training{
    private String companyName;
    private String country;
    private String Details;
    private Long oseId;

    // Here, I have implemented getter and setter methods instead of lombok annotations

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public Long getOseId() {
        return oseId;
    }

    public void setOseId(Long oseId) {
        this.oseId = oseId;
    }
}

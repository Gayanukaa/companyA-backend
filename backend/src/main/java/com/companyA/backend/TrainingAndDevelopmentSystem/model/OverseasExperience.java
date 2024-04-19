package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;

@Data
public class OverseasExperience extends Training{
    private String companyName;
    private String country;
    private String Details;
    private Long oseId;

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

package com.companyA.backend.TrainingAndDevelopmentSystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class Products {
    @Id
    private String productId;

    private String productName;
    private String productDetails;

    public boolean productCompleted(String productId, boolean qaApproval){
        if (qaApproval){
            return true;
        }
        else {
            return false;
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }
}

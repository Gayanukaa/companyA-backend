package com.companyA.backend.GeneralManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountDTO {
    private long customerCount;
    private long managerCount;


    public long getCustomerCount(){
        return customerCount;
    }

    public long getManagerCount(){
        return managerCount;
    }
}

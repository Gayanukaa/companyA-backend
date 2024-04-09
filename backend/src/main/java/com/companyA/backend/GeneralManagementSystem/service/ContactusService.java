package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Contactus;
import com.companyA.backend.GeneralManagementSystem.repository.ContactusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactusService {

    private ContactusRepository contactusRepository;

    public String Contactusmessage (Contactus contactus){
        contactusRepository.save(contactus);
        return "Massage successfully delived";
    }
}

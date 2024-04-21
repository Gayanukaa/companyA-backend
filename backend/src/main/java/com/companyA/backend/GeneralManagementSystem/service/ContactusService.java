package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Contactus;
import com.companyA.backend.GeneralManagementSystem.repository.ContactusRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactusService {

    private ContactusRepository contactusRepository;

    public String ContactusMessage (Contactus contactus){
        contactusRepository.save(contactus);
        return "Message successfully delivered";
    }

    public ResponseEntity<List<Contactus>> viewAllFeedbacks (){
        List<Contactus> feedbackList = contactusRepository.findAll();
        return new ResponseEntity<List<Contactus>>(feedbackList, HttpStatus.OK);
    }
}

package com.companyA.backend.GeneralManagementSystem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contactus")
@AllArgsConstructor

public class ContactusController {
    //customer feedback
    @PostMapping("/feedback")
    public ResponseEntity<Map<String,String>>customerFeedbackControl(@Valid @RequestBody Contactus contactus ){
        String feedbackdetails = contactusServise.customerFeedback(contactus);
        Map<String, String> response = new HashMap<>();
        response.put("message",feedbackdetails);
    }

}

package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.model.Contactus;
import com.companyA.backend.GeneralManagementSystem.service.ContactusService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
@CrossOrigin
public class ContactusController {
    private ContactusService contactusService;

    //customer feedback
    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> customerFeedbackControl(@Valid @RequestBody Contactus contactus) {
        String feedbackDetails = contactusService.ContactusMessage(contactus);
        Map<String, String> response = new HashMap<>();
        response.put("message", feedbackDetails);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // View Feedbacks
    @GetMapping("/view")
    public ResponseEntity<List<Contactus>> viewFeedbackControl() {
        return contactusService.viewAllFeedbacks();
    }
}



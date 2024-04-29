package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Contactus;
import com.companyA.backend.GeneralManagementSystem.repository.ContactusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ContactusService {

    private ContactusRepository contactusRepository;
    private MongoTemplate mongoTemplate;

    public String ContactusMessage (Contactus contactus){
        contactusRepository.save(contactus);
        return "Message successfully delivered";
    }

    public ResponseEntity<List<Contactus>> viewAllFeedbacks (){
        List<Contactus> feedbackList = contactusRepository.findAll();
        return new ResponseEntity<List<Contactus>>(feedbackList, HttpStatus.OK);
    }


    public ResponseEntity<Map<String, Object>> MarkAsRead(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().set("isRead", 1);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Contactus.class);

        // Construct response map
        Map<String, Object> response = new HashMap<>();
        if (result.getModifiedCount() > 0) {
            response.put("status", true);
            response.put("message", "Message with ID " + id + " marked as read.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("status", false);
            response.put("message", "Message with ID " + id + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }
}

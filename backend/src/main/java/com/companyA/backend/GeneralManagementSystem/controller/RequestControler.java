package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.model.Requests;
import com.companyA.backend.GeneralManagementSystem.service.RequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/request")
@AllArgsConstructor
@CrossOrigin
public class RequestControler {

    private RequestService requestService;

    @PostMapping("/post")
    public ResponseEntity<Map<String, String>> postRequest(@Valid @RequestBody Requests requests) {
        String requestDetails = requestService.postRequest(requests);
        Map<String, String> response = new HashMap<>();
        response.put("message", requestDetails);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // View Feedbacks
    @GetMapping("/view")
    public ResponseEntity<List<Requests>> viewAllRequests() {
        return requestService.viewAllRequests();
    }

    @PostMapping("/approve")
    public void approveRequest(@RequestParam("id") String id) { requestService.approveRequest(id); }

    @PostMapping("/reject")
    public void rejectRequest(@RequestParam("id") String id) { requestService.rejectRequest(id); }
}

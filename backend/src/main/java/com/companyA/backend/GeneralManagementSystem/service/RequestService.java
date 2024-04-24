package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Requests;
import com.companyA.backend.GeneralManagementSystem.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestService {

    private RequestRepository requestRepository;

    public String postRequest (Requests requests){
        requestRepository.save(requests);
        return "Request successfully posted";
    }

    public ResponseEntity<List<Requests>> viewAllRequests (){
        List<Requests> requestList = requestRepository.findAll();
        return new ResponseEntity<List<Requests>>(requestList, HttpStatus.OK);
    }

}

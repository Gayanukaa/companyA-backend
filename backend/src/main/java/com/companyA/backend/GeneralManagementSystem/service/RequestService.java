package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.model.Requests;
import com.companyA.backend.GeneralManagementSystem.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void approveRequest(String requestId) {
        Optional<Requests> temp = requestRepository.findById(requestId);
        if (temp.isPresent()) {
            Requests temp2 = temp.get();
            temp2.setStatus(1);
            requestRepository.save(temp2);
        }
    }

    public void rejectRequest(String requestId) {
        Optional<Requests> temp = requestRepository.findById(requestId);
        if (temp.isPresent()) {
            Requests temp2 = temp.get();
            temp2.setStatus(2);
            requestRepository.save(temp2);
        }
    }

}

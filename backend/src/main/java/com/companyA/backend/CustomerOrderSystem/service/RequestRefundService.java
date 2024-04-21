package com.companyA.backend.CustomerOrderSystem.service;
import com.companyA.backend.CustomerOrderSystem.model.RequestRefund;
import com.companyA.backend.CustomerOrderSystem.repository.RequestRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RequestRefundService {
    @Autowired
    private RequestRefundRepository requestRefundRepository;

    public void requestRefund(RequestRefund order_ID) {requestRefundRepository.save(order_ID);
    }
}


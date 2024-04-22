package com.companyA.backend.CustomerOrderSystem.service;
import java.util.Optional;

import com.companyA.backend.CustomerOrderSystem.model.OrderStatus;
import com.companyA.backend.CustomerOrderSystem.repository.OrderStatusRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public Optional<OrderStatus> getOrderStatus(String order_ID) {
        ObjectId objectId = new ObjectId(order_ID);
        return orderStatusRepository.findById(objectId);
    }

}

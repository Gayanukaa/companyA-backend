package com.companyA.backend.CustomerOrderSystem.service;
import java.util.Optional;
import com.companyA.backend.CustomerOrderSystem.model.RefundStatus;
import com.companyA.backend.CustomerOrderSystem.repository.RefundStatusRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RefundStatusService {
    @Autowired
    private RefundStatusRepository refundStatusRepository;

    public Optional<RefundStatus> getRefundStatus(String order_ID) {
        ObjectId objectId = new ObjectId(order_ID);
        return refundStatusRepository.findById(objectId);

    }
}
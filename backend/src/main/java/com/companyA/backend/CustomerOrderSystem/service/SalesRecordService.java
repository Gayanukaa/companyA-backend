package com.companyA.backend.CustomerOrderSystem.service;
import java.util.Optional;
import com.companyA.backend.CustomerOrderSystem.model.SalesRecord;
import com.companyA.backend.CustomerOrderSystem.repository.SalesRecordRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesRecordService {
    @Autowired
    private SalesRecordRepository salesRecordRepository;

    public Optional<SalesRecord> getOrderHistory(ObjectId user_Id) {
        return salesRecordRepository.findById(user_Id);
    }
}

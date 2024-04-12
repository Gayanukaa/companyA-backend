package com.companyA.backend.CustomerOrderSystem.service;
import com.companyA.backend.CustomerOrderSystem.model.GetQuotation;
import com.companyA.backend.CustomerOrderSystem.repository.GetQuotationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GetQuotationService {
    @Autowired
    private GetQuotationRepository getQuotationRepository;

    public Optional<GetQuotation> getQuotation(ObjectId quotation_ID) {
        return getQuotationRepository.findById(quotation_ID);

    }
}
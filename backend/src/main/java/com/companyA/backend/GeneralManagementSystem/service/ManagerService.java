package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagerService {

    private ManagerRepository managerRepository;

    public String createManager(Manager manager) {
        managerRepository.save(manager);
        return "Manager for the specific section is added Successfully";
    }
}

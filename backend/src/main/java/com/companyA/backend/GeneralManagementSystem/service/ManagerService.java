package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.DTO.ManagerDTO;
import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManagerService {

    private ManagerRepository managerRepository;

    public String createManager(Manager manager) {
        String tempEmail = manager.getEmail();
        Manager ExistingManager = managerRepository.findByEmail(tempEmail);
        if (ExistingManager!=null) {
            String tempRoll = ExistingManager.getRole();
            return "The personnel with the specific email is already assigned to "+tempRoll+".";
        }
        else {
            managerRepository.save(manager);
            return "Manager added Successfully";
        }

    }

    public Optional<Manager> findManagerById(String managerId) {
        return managerRepository.findById(managerId);
    }

    public String updatemanagerById(Manager manager) {
        String managerId = manager.getId();
        managerRepository.deleteById(managerId);
        managerRepository.save(manager);
        return "Manager updated successfully.";

    }

    public List<ManagerDTO> viewAllManagers(){
        List<Manager> managers = managerRepository.findAll();
        List<ManagerDTO> managerDTOs = new ArrayList<>();
        for (Manager manager : managers) {
            ManagerDTO dto = new ManagerDTO();
            dto.setId(manager.getId());
            dto.setMobileNumber(manager.getMobileNumber());
            dto.setEmail(manager.getEmail());
            dto.setRole(manager.getRole());
            dto.setFirstName(manager.getFirstName());
            dto.setLastName(manager.getLastName());
            managerDTOs.add(dto);
        }
        return managerDTOs;
    }

}

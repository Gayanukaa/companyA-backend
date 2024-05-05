package com.companyA.backend.GeneralManagementSystem.service;

import com.companyA.backend.GeneralManagementSystem.DTO.ManagerDTO;
import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ManagerService {

    private ManagerRepository managerRepository;
    private PasswordEncoder passwordEncoder;

    public String createManager(Manager manager) {
        String tempEmail = manager.getEmail();
        Manager ExistingManager = managerRepository.findByEmail(tempEmail);
        if (ExistingManager!=null) {
            String tempRoll = ExistingManager.getRole();
            return "The personnel with the specific email is already assigned to "+tempRoll+".";
        }
        else {
            String hashedPassword = passwordEncoder.encode(manager.getPassword());
            manager.setPassword(hashedPassword);
            manager.setIsDeleted(0);
            managerRepository.save(manager);
            return "Manager added Successfully";
        }
    }

    public Optional<Manager> findManagerById(String managerId) {
        return managerRepository.findById(managerId);
    }

    public String updateManagerById(Manager manager) {
        String managerId = manager.getId();
        Optional<Manager> availableManager = managerRepository.findById(managerId);

        if (availableManager.isPresent()) {
            Manager existingManager = availableManager.get();
            String password = existingManager.getPassword();
            Integer isDeleted = existingManager.getIsDeleted();

            managerRepository.deleteById(managerId);
            manager.setPassword(password);
            manager.setIsDeleted(isDeleted);
            managerRepository.save(manager);

            return "Manager updated successfully.";
        } else {
            return "Manager with ID " + managerId + " not found.";
        }
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
            dto.setIsDeleted(manager.getIsDeleted());
            managerDTOs.add(dto);
        }
        return managerDTOs;
    }


    public long countManagers(){
        List<Manager>managers=managerRepository.findAll();

        List<Manager> activeManagers = managers.stream()
                .filter(manager -> manager.getIsDeleted() == 0)
                .toList();

        return activeManagers.size()-3;
    }


    public ResponseEntity<Map<String, String>> deleteManagerById(ObjectId managerId) {
        Optional<Manager> optionalManager = managerRepository.findById(String.valueOf(managerId));
        Map<String, String> response = new HashMap<>();

        if (optionalManager.isPresent()) {
            Manager manager = optionalManager.get();
            if (manager.getIsDeleted() == 0) {
                manager.setIsDeleted(1);
                managerRepository.save(manager);
                response.put("message", "Manager marked as deleted.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "Manager is already deleted.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.put("message", "Manager not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}

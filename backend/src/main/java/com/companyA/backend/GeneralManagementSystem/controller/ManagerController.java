package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.DTO.ManagerDTO;
import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.service.ManagerService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/manager")
@AllArgsConstructor
@CrossOrigin
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/createaccount")
    public String createManagerHandle(@RequestBody Manager manager) {
        return managerService.createManager(manager);

    }

    @GetMapping("/findById")
    public Optional<Manager> findManagerById(@RequestParam("id") String id){
        return managerService.findManagerById(id);
    }

    @PostMapping("/updateById")
    public String updateManager(@RequestBody Manager manager){
        return managerService.updateManagerById(manager);
    }

    @GetMapping("/viewAllManagers")
    public List<ManagerDTO> viewAllManagers(){
        return managerService.viewAllManagers();
    }


    @DeleteMapping("/deleteManager")
    public ResponseEntity <Map<String, String>> deleteManagerHandle(@RequestParam("id") ObjectId id){
        return managerService.deleteManagerById(id);
    }


}

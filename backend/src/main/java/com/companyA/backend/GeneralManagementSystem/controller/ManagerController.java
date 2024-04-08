package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {

    private ManagerService managerService;

    @PostMapping("/createaccount")
    public String createManagerHandle(@RequestBody Manager manager) {
        return managerService.createManager(manager);
    }

    @GetMapping("/findById")
    public Optional<Manager> findManagerById(@RequestParam("id") String id){
        return managerService.findManagerById(id);
    }

}

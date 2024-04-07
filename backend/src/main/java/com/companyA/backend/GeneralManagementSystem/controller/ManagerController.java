package com.companyA.backend.GeneralManagementSystem.controller;

import com.companyA.backend.GeneralManagementSystem.model.Manager;
import com.companyA.backend.GeneralManagementSystem.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {

    private ManagerService managerService;

    @PostMapping("/createaccount")
    public String createManagerHandle(@RequestBody Manager manager) {
        return managerService.createManager(manager);
    }
}

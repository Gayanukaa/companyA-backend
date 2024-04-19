package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.EmployeeDetailModel;
import com.companyA.backend.HumanResourceSystem.model.TrainingManagementModel;
import com.companyA.backend.HumanResourceSystem.repository.EmployeeDetailRepository;
import com.companyA.backend.HumanResourceSystem.repository.TrainingManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/Training")
public class TrainingManagementController {

    @Autowired
    TrainingManagementRepository trainingManagementRepository;

    @Autowired
    EmployeeDetailRepository employeeDetailRepository;

    @GetMapping("/GetDetails/{employeeId}")
    public ResponseEntity<EmployeeDetailModel> getEmployeeDetail(@PathVariable String employeeId){
        EmployeeDetailModel employee = employeeDetailRepository.findByIdAndIsActive(employeeId,true);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);

    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<TrainingManagementModel> updateEmployee(@PathVariable String id, @RequestBody TrainingManagementModel updateStatus) {
        EmployeeDetailModel status = employeeDetailRepository.findById(id).orElse(null);
        if (status == null) {
            return ResponseEntity.notFound().build();
        }

        status.setSkillLevel(updateStatus.getSkillLevel());
        employeeDetailRepository.save(status);
        return ResponseEntity.ok(updateStatus);
    }



}

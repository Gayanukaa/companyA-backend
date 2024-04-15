package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.TrainingManagementModel;
import com.companyA.backend.HumanResourceSystem.repository.TrainingManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Training")
public class TrainingManagementController {

    @Autowired
    TrainingManagementRepository trainingManagementRepository;

    @GetMapping("/{id}")
    public ResponseEntity<TrainingManagementModel> getSkillLevelAndDepartment(@PathVariable String id ){
        TrainingManagementModel trainee = trainingManagementRepository.findById(id);
        if (trainee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trainee);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingManagementModel> updateSkillLevel(@PathVariable String id,@RequestBody TrainingManagementModel updateSkillLevel ){
        TrainingManagementModel updateStatus = trainingManagementRepository.findById(id);
        if (updateStatus == null) {
            return ResponseEntity.notFound().build();
        }

        trainingManagementRepository.save(updateSkillLevel);
        return ResponseEntity.ok(updateSkillLevel);
    }

}

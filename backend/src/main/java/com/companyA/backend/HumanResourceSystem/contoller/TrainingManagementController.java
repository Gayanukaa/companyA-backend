package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.EmployeeDetailModel;
import com.companyA.backend.HumanResourceSystem.model.TrainingManagementModel;
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

    //TO get existing record
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TrainingManagementModel>> getSkillLevelAndDepartment(@PathVariable String id ){
        Optional<TrainingManagementModel> trainee = trainingManagementRepository.findById(id);
        if (trainee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trainee);

    }
    // update existing record
    @PutMapping("/{id}")
    public ResponseEntity<TrainingManagementModel> updateSkillLevel(@PathVariable String id,@RequestBody TrainingManagementModel updateSkillLevel ){
        Optional<TrainingManagementModel> updateStatus = trainingManagementRepository.findById(id);
        if (updateStatus.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        trainingManagementRepository.save(updateSkillLevel);
        return ResponseEntity.ok(updateSkillLevel);
    }

    // Create new record
    @PostMapping("/Create")
    public ResponseEntity<TrainingManagementModel> insertTrainingDetails(@RequestBody TrainingManagementModel trainingManagementModel) {
        TrainingManagementModel savedTrainee = trainingManagementRepository.save(trainingManagementModel);
        return ResponseEntity.ok(savedTrainee);

    }


    // Delete existing record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainingDetails(@PathVariable String id) {
        TrainingManagementModel existingTrainee = trainingManagementRepository.findById(id).orElse(null);
        if (existingTrainee == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            trainingManagementRepository.delete(existingTrainee);
            return ResponseEntity.ok("Successfully deleted");

        }

    }

}

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
@RequestMapping("/Courses")
public class TrainingManagementController {

    @Autowired
    TrainingManagementRepository trainingManagementRepository;

    //TO get existing record
    @GetMapping("/{courseId}")
    public ResponseEntity<Optional<TrainingManagementModel>> getCourseDetails(@PathVariable String courseId ){
        Optional<TrainingManagementModel> course = trainingManagementRepository.findByCourseId(courseId);
        if (course.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);

    }
    // update existing record
    @PutMapping("/{courseId}")
    public ResponseEntity<TrainingManagementModel> updateSkillLevel(@PathVariable String courseId,@RequestBody TrainingManagementModel updateCourseDetails ){
        Optional<TrainingManagementModel> updateCourse = trainingManagementRepository.findById(courseId);
        if (updateCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        trainingManagementRepository.save(updateCourseDetails);
        return ResponseEntity.ok(updateCourseDetails);
    }

    // Create new record
    @PostMapping("/Create")
    public ResponseEntity<TrainingManagementModel> insertCourseDetails(@RequestBody TrainingManagementModel trainingManagementModel) {
        TrainingManagementModel savedCourse = trainingManagementRepository.save(trainingManagementModel);
        return ResponseEntity.ok(savedCourse);

    }


    // Delete existing record
    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteTrainingDetails(@PathVariable String courseId) {
        TrainingManagementModel existingCourse = trainingManagementRepository.findById(courseId).orElse(null);
        if (existingCourse == null) {
            return ResponseEntity.notFound().build();
        }
        else{
            trainingManagementRepository.delete(existingCourse);
            return ResponseEntity.ok("Successfully deleted");

        }

    }

}

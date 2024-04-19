package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;


import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.Course;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.OverseasExperience;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.OverseasExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OverseasExperienceController {
    @Autowired
    private OverseasExperienceRepository overseasExperienceRepository;

    @PostMapping("/api/tms/overseas")
    OverseasExperience newCourse(@RequestBody OverseasExperience newCourse){
        return overseasExperienceRepository.save(newCourse);
    }

    @GetMapping("/api/tms/courses(overseas)")
    List<OverseasExperience> getAllCourses(){
        return overseasExperienceRepository.findAll();
    }

    @GetMapping("/api/tms/overseas/{id}")
    OverseasExperience getCourseById(@PathVariable String id){
        return overseasExperienceRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

}

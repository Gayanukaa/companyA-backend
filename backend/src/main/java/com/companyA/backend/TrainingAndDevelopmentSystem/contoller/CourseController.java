package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.Course;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/api/tms/course")
    Course newCourse(@RequestBody Course newCourse){
        return courseRepository.save(newCourse);
    }

    @GetMapping("/api/tms/courses")
    List<Course> getAllUsers(){
        return courseRepository.findAll();
    }

    @GetMapping("/api/tms/course/{id}")
    Course getUserById(@PathVariable String id){
        return courseRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }


}

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
    List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    @GetMapping("/api/tms/course/{id}")
    Course getCourseById(@PathVariable String id){
        return courseRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/course/{id}")
    Course updateCourse(@RequestBody Course newUser,@PathVariable String id){
        return courseRepository.findById(id)
                .map(user -> {
                    user.setCourseName(newUser.getCourseName());
                    user.setInstructor(newUser.getInstructor());
                    user.setLink(newUser.getLink());
                    user.setDetails(newUser.getDetails());
                    user.setCourseId(newUser.getCourseId());
                    return courseRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/course/{id}")
    String deleteCourse(@PathVariable String id){
        if(!courseRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        courseRepository.deleteById(id);
        return "Course with "+id+" has been deleted successfully";
    }



}
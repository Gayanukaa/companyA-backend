package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.Course;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.OverseasExperience;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
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
    @PutMapping("/api/tms/course/{id}")
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

    @DeleteMapping("/api/tms/course/{id}")
    String deleteCourseById(@PathVariable String id){
        if(!courseRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        courseRepository.deleteById(id);
        return "Course with "+id+" has been deleted successfully";
    }

    //getCourseLinkByID method implement here
    @GetMapping("/api/tms/course/{courseId}/link")
    public String getCourseLinkById(@PathVariable Long courseId) {
        return courseRepository.findCourseLinkById(courseId);
    }

    //getCourseDetails method implement here
    @GetMapping("/api/tms/course/{id}/details")
    public ResponseEntity<String> getCourseDetails(@PathVariable String id) {
        // Find overseas experience by ID
        Course course = courseRepository.findById(id).orElse(null);

        // Check if the experience exists
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect ID: " + id);
        }

        // Check if details are not null
        if (course.getDetails() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Details not found for ID: " + id);
        }

        // Return details string
        return ResponseEntity.ok(course.getDetails());
    }

}

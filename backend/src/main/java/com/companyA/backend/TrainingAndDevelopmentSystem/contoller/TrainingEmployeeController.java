package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.Course;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.TrainingEmployee;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.TrainingEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin
public class TrainingEmployeeController {
    @Autowired
    private TrainingEmployeeRepository trainingEmployeeRepository;
    @GetMapping("/api/tms/get-tm")
    public List<TrainingEmployee> getAllTMs() {
        return trainingEmployeeRepository.findAll();
    }
    @GetMapping("/api/tms/get-tm/{id}")
    public TrainingEmployee getTMById(String id) {
        return trainingEmployeeRepository.findById((id)).orElse(null);
    }

    @PostMapping("/api/tms/add-tm")
    public TrainingEmployee createTM(@RequestBody TrainingEmployee user) {
        return trainingEmployeeRepository.save(user);
    }

    @PostMapping("/api/tms/get-tm/signup")
    public ResponseEntity<String> signTM(@RequestBody TrainingEmployee user) {
        // Check if the email already exists
        if (trainingEmployeeRepository.existsByEmployeeEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already exists");
        }

        // Save the user if the email is unique
        trainingEmployeeRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    public TrainingEmployee login(String email, String password) {
        return trainingEmployeeRepository.findByEmailAndPassword(email, password);
    }

    @PostMapping("/api/tms/get-tm/login")
    public ResponseEntity<String> login(@RequestBody TrainingEmployeeController.LoginRequest loginRequest) {
        TrainingEmployee user = login(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    //enroll method implements here
    @PutMapping("/api/tms/{email}/enroll/{courseId}")
    public ResponseEntity<String> enroll(@PathVariable String email, @PathVariable Long courseId) {
        // Check if user with the provided email exists
        boolean userExists = trainingEmployeeRepository.existsByEmployeeEmail(email);
        if (!userExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found");
        }

        // User exists, proceed with enrollment
        TrainingEmployee user = trainingEmployeeRepository.findByEmployeeEmail(email);
        List<Long> enrolledCourses = user.getEnrolledCourses();
        enrolledCourses.add(courseId);
        user.setEnrolledCourses(enrolledCourses);
        trainingEmployeeRepository.save(user);

        return ResponseEntity.ok("Enrollment successful for user with email " + email);
    }

    //complete method implement here
    @PutMapping("/api/tms/{email}/complete/{courseId}")
    public ResponseEntity<String> complete(@PathVariable String email, @PathVariable Long courseId) {
        // Check if user with the provided email exists
        boolean userExists = trainingEmployeeRepository.existsByEmployeeEmail(email);
        if (!userExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found");
        }

        // User exists, proceed with enrollment
        TrainingEmployee user = trainingEmployeeRepository.findByEmployeeEmail(email);
        List<Long> completedCourses = user.getCompletedCourses();
        completedCourses.add(courseId);
        user.setCompletedCourses(completedCourses);
        trainingEmployeeRepository.save(user);

        return ResponseEntity.ok("Course completion recorded successfully for user: " + email);
    }


}

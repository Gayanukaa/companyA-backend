package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.Course;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.TrainingEmployee;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.TrainingEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
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
                    .body("Username already exists");
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
        // this is for cross APIs

//    @PutMapping("/user/{username}/enroll/{courseId}")
//    public ResponseEntity<String> enrollUserInCourse(@PathVariable String username, @PathVariable Long courseId) {
//        TrainingEmployee user = userRepository.findByUsername(username)
//                .orElseThrow();
//        List<Long> enrolledCourses = user.getEnrolledCourses();
//        enrolledCourses.add(courseId);
//        user.setEnrolledCourses(enrolledCourses);
//        userRepository.save(user);
//        Course use= new Course();
//        return ResponseEntity.ok(use.enroll(4554));
//    }
}

package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.MainSupervisor;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.MainSupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MainSupervisorController {
    @Autowired
    private MainSupervisorRepository mainSupervisorRepository;
    public MainSupervisor login(String email, String password) {
        return mainSupervisorRepository.findByEmailAndPassword(email, password);
    }
    @PostMapping("/api/tms/main-supervisor")
    MainSupervisor newUser(@RequestBody MainSupervisor newUser){
        return mainSupervisorRepository.save(newUser);
    }

    @GetMapping("/api/tms/main-supervisor")
    List<MainSupervisor> getAllUsers(){
        return mainSupervisorRepository.findAll();
    }

    @PostMapping("/api/tms/main-supervisor/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        MainSupervisor supervisor = login(loginRequest.getEmail(), loginRequest.getPassword());
        if (supervisor != null) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
    public static class LoginRequest {
        private String mSupervisorEmail;
        private String mPassword;

        public String getEmail() {
            return mSupervisorEmail;
        }

        public void setEmail(String name) {
            this.mSupervisorEmail = name;
        }

        public String getPassword() {
            return mPassword;
        }

        public void setPassword(String password) {
            this.mPassword = password;
        }
    }

    @DeleteMapping("/api/tms/main-supervisor/delete/{id}")
    String deleteMainSupervisor(@PathVariable String id){
        if(!mainSupervisorRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        mainSupervisorRepository.deleteById(id);
        return "Main Supervisor with "+id+" has been deleted successfully";
    }

    //takeActionsManually method implement here
    @GetMapping("/api/tms/main-supervisor/take-manual-actions")
    public String takeActionsManually() {
        return "I prefer to take actions manually, ensuring a personal touch and precision in every step. ";
    }
}

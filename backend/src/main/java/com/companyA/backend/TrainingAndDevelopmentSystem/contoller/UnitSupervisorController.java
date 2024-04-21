package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.UnitSupervisor;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.UnitSupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UnitSupervisorController {
    @Autowired
    private UnitSupervisorRepository unitSupervisorRepository;
    public UnitSupervisor login(String email, String password) {
        return unitSupervisorRepository.findByEmailAndPassword(email, password);
    }
    @PostMapping("/api/tms/unit-supervisor")
    UnitSupervisor newUnitSupervisor(@RequestBody UnitSupervisor newUser){
        return unitSupervisorRepository.save(newUser);
    }

    @GetMapping("/api/tms/unit-supervisor")
    List<UnitSupervisor> getAllUnitSupervisor(){
        return unitSupervisorRepository.findAll();
    }

    @PostMapping("/api/tms/unit-supervisor/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UnitSupervisor supervisor = login(loginRequest.getEmail(), loginRequest.getPassword());
        if (supervisor != null) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
    public static class LoginRequest {
        private String uSupervisorEmail;
        private String uPassword;

        public String getEmail() {
            return uSupervisorEmail;
        }

        public void setEmail(String email) {
            this.uSupervisorEmail = email;
        }

        public String getPassword() {
            return uPassword;
        }

        public void setPassword(String password) {
            this.uPassword = password;
        }
    }
    @DeleteMapping("/api/tms/unit-supervisor/delete/{id}")
    String deleteUnitSupervisor(@PathVariable String id){
        if(!unitSupervisorRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        unitSupervisorRepository.deleteById(id);
        return "User with "+id+" has been deleted successfully";
    }
}

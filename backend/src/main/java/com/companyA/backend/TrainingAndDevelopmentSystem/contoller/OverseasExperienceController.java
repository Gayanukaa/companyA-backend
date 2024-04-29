package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;


import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.Course;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.OverseasExperience;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.OverseasExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class OverseasExperienceController {
    @Autowired
    private OverseasExperienceRepository overseasExperienceRepository;

    @PostMapping("/api/tms/overseas")
    OverseasExperience newOSE(@RequestBody OverseasExperience newCourse){
        return overseasExperienceRepository.save(newCourse);
    }

    @GetMapping("/api/tms/overseas")
    List<OverseasExperience> getAllOSEs(){
        return overseasExperienceRepository.findAll();
    }

    @GetMapping("/api/tms/overseas/{id}")
    OverseasExperience getOSEById(@PathVariable String id){
        return overseasExperienceRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/api/tms/overseas/{id}")
    String deleteOSE(@PathVariable String id){
        if(!overseasExperienceRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        overseasExperienceRepository.deleteById(id);
        return "User with "+id+" has been deleted successfully";
    }

    @PutMapping("/api/tms/overseas/{id}")
    OverseasExperience updateOSE(@RequestBody OverseasExperience newUser,@PathVariable String id){
        return overseasExperienceRepository.findById(id)
                .map(user -> {
                    user.setCompanyName(newUser.getCompanyName());
                    user.setCountry(newUser.getCountry());
                    user.setOseId(newUser.getOseId());
                    user.setDetails(newUser.getDetails());
                    user.setDuration(newUser.getDuration());
                    user.setCost(newUser.getCost());
                    return overseasExperienceRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    //getExperienceDetails method implement here
    @GetMapping("/api/tms/overseas/{id}/details")
    public ResponseEntity<String> getExperienceDetails(@PathVariable String id) {
        // Find overseas experience by ID
        OverseasExperience experience = overseasExperienceRepository.findById(id).orElse(null);

        // Check if the experience exists
        if (experience == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect ID: " + id);
        }

        // Check if details are not null
        if (experience.getDetails() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Details not found for ID: " + id);
        }

        // Return details string
        return ResponseEntity.ok(experience.getDetails());
    }
}

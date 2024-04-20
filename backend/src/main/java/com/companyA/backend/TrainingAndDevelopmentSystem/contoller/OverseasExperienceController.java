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
}

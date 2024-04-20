package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.exception.UserNotFoundException;
import com.companyA.backend.TrainingAndDevelopmentSystem.model.Prototyping;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.ProductDevelopmentRepository;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.PrototypingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PrototypingController {
    @Autowired
    private PrototypingRepository prototypingRepository;
    @Autowired
    private ProductDevelopmentRepository productDevelopmentRepository;

    @PostMapping("/api/tms/prototype")
    Prototyping newPrototype(@RequestBody Prototyping newPrototype){
        return prototypingRepository.save(newPrototype);
    }

    @GetMapping("/api/tms/prototypes")
    List<Prototyping> getAllPrototypes(){
        return prototypingRepository.findAll();
    }

    @GetMapping("/api/tms/prototype/{id}")
    Prototyping getPrototypeById(@PathVariable String id){
        return prototypingRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/api/tms/prototype/{id}")
    Prototyping updatePrototype(@RequestBody Prototyping newPrototype,@PathVariable String id){
        return prototypingRepository.findById(id)
                .map(prototype -> {
                    prototype.setPrototypeName(newPrototype.getPrototypeName());
                    prototype.setPrototypeType(newPrototype.getPrototypeType());
                    return prototypingRepository.save(prototype);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/api/tms/prototype/{id}")
    String deleteUser(@PathVariable String id){
        if(!prototypingRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        prototypingRepository.deleteById(id);
        return "Prototype with "+id+" has been deleted successfully";
    }
}

package com.companyA.backend.TrainingAndDevelopmentSystem.contoller;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.Progress;
import com.companyA.backend.TrainingAndDevelopmentSystem.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProgressController {
    private final ProgressRepository progressRepository;

    @Autowired
    public ProgressController(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @PostMapping("/api/tms/progress")
    public ResponseEntity<?> createProgress(@RequestBody Progress progress) {
        try {
            progressRepository.save(progress);
            return new ResponseEntity<>("Progress created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

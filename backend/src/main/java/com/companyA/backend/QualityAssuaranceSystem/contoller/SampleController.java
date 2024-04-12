package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.Sample;
import com.companyA.backend.QualityAssuaranceSystem.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Make necessary changes

@RestController
@RequestMapping("/api/v1/samples")
public class SampleController {

    @Autowired
    private SampleService service;

    @PostMapping("/addSample")
    @ResponseStatus(HttpStatus.CREATED)
    public Sample addSample(@RequestBody Sample sample) {
        return service.addSample(sample);
    }

    @GetMapping
    public List<Sample> getAllSamples() {
        return service.getAllSamples();
    }

    @GetMapping("/getSample/{id}")
    public Optional<Sample> getSampleById(@PathVariable String id) {
        return service.getSampleById(id);
    }
}

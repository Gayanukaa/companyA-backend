package com.companyA.backend.QualityAssuaranceSystem.contoller;

import com.companyA.backend.QualityAssuaranceSystem.model.Test;
import com.companyA.backend.QualityAssuaranceSystem.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tests")
@CrossOrigin
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/addTest")
    @ResponseStatus(HttpStatus.CREATED)
    public Test addTest(@RequestBody Test test) {
        return testService.addTest(test);
    }

    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/getTest/{id}")
    public Optional<Test> getTestById(@PathVariable String id) {
        return testService.getTestById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTest(@PathVariable String id){
        return testService.deleteTestById(id);
    }
}

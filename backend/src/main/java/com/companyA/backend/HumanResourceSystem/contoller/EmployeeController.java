package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.EmployeeDetailModel;
import com.companyA.backend.HumanResourceSystem.repository.EmployeeDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/Employee")
public class EmployeeController {

    @Autowired
    EmployeeDetailRepository employeeDetailRepository;


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDetailModel> getEmployeeDetail(@PathVariable String employeeId) {
        EmployeeDetailModel employee = employeeDetailRepository.findByEmployeeIdAndIsActive(employeeId,true);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<EmployeeDetailModel>> getEmployeesByLastName(@PathVariable String lastName) {
        List<EmployeeDetailModel> employees = employeeDetailRepository.findByLastNameAndIsActive(lastName,true);
        if (employees == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }

    // Insert Employee
    @PostMapping("/Create")
    public ResponseEntity<EmployeeDetailModel> insertEmployee(@RequestBody EmployeeDetailModel employeeDetailModel) {
        EmployeeDetailModel savedEmployee = employeeDetailRepository.save(employeeDetailModel);
        return ResponseEntity.ok(savedEmployee);
    }


    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDetailModel> updateEmployee(@PathVariable String employeeId, @RequestBody EmployeeDetailModel updatedEmployee) {
        EmployeeDetailModel existingEmployee = (EmployeeDetailModel) employeeDetailRepository.findByEmployeeIdAndIsActive(employeeId,true);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        // set fields

        // Continue updating other fields as needed
        employeeDetailRepository.save(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }



    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        EmployeeDetailModel existingEmployee = employeeDetailRepository.findById(employeeId).orElse(null);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        //update isActive flag to false
        existingEmployee.setActive(false);
        employeeDetailRepository.save(existingEmployee);
        return ResponseEntity.ok("Successfully deleted");
    }



}

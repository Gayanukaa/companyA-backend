package com.companyA.backend.HumanResourceSystem.contoller;

import com.companyA.backend.HumanResourceSystem.model.EmployeeDetailModel;
import com.companyA.backend.HumanResourceSystem.repository.EmployeeDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/Employee")
public class EmployeeController {

    @Autowired
    EmployeeDetailRepository employeeDetailRepository;


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailModel> getEmployeeDetail(@PathVariable String id) {
        EmployeeDetailModel employee = employeeDetailRepository.findByIdAndIsActive(id,true);
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


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDetailModel> updateEmployee(@PathVariable String id, @RequestBody EmployeeDetailModel updatedEmployee) {
        EmployeeDetailModel existingEmployee = employeeDetailRepository.findById(id).orElse(null);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        // set fields

        // Continue updating other fields as needed
        employeeDetailRepository.save(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        EmployeeDetailModel existingEmployee = employeeDetailRepository.findById(id).orElse(null);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        //update isActive flag to false
        existingEmployee.setActive(false);
        employeeDetailRepository.save(existingEmployee);
        return ResponseEntity.ok("Successfully deleted");
    }



}

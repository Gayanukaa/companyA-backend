package com.companyA.backend.LogisticsAndMaintenanceSystem.contoller;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Technician;
import com.companyA.backend.LogisticsAndMaintenanceSystem.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/T1")
@CrossOrigin
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    @GetMapping("/getTechnicians")
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        return new ResponseEntity<>(technicianService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/technicianById/{technicianId}")
    public ResponseEntity<Optional<Technician>> getTechnicianById(@PathVariable String technicianId) {
        return new ResponseEntity<>(technicianService.technicianById(technicianId), HttpStatus.OK);
    }

    @PostMapping("/addTechnician")
    public ResponseEntity<Map<String , String >> setTechnician(@RequestBody Technician technician) {
        technicianService.add(technician);
        Map<String , String > response = new HashMap<>();
        response.put("message", "Technician added successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @PatchMapping("/updateTechnician/{id}")
    public Technician updateTechnician(@PathVariable String id, @RequestBody Map<String,Object> fields) {
         return technicianService.updateTechnician(id,fields);
    }







    @DeleteMapping("/deleteTechnician/{technicianId}")
    public ResponseEntity<String> deleteTechnician(@PathVariable String technicianId) {
        technicianService.deleteTechnician(technicianId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/checkAvailability/{technicianId}")
    public ResponseEntity<Boolean> getAvailability(@PathVariable String technicianId) {
        Optional<Technician> optionalTechnician = technicianService.technicianById(technicianId);

        if (optionalTechnician.isPresent()) {
            return new ResponseEntity<>(optionalTechnician.get().getAvailability(), HttpStatus.OK);
        } else {
            // Handle the case where the machine wasn't found (optional)
            throw new RuntimeException("Technician not found with id: " + technicianId);
            // OR: return ResponseEntity.notFound().build(); (to indicate not found)
        }
    }



}



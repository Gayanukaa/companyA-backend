package com.companyA.backend.LogisticsAndMaintenanceSystem.contoller;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Machinery;
import com.companyA.backend.LogisticsAndMaintenanceSystem.service.MachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/m1") // Sets the base URI for all resource URIs.
@CrossOrigin
public class MachineController {


    @Autowired
    private MachineryService machineryService;  // Injects the MachineryService to handle the business logic.

    // Retrieves a list of all machinery, returning them with an HTTP OK status.
    @GetMapping("/getMachines")
    public ResponseEntity<List<Machinery>> getAllMachinery() {
        return new ResponseEntity<List<Machinery>>(machineryService.findAll(), HttpStatus.OK);
    }


    // Retrieves a single machinery by its machineId, returns it.
    @GetMapping("/byId/{machineId}")
    public ResponseEntity<Optional<Machinery>> getMachineryById(@PathVariable String machineId) {
        return new ResponseEntity<Optional<Machinery>>(machineryService.machineryById(machineId), HttpStatus.OK);
    }

    // Retrieves machinery by name, returns it.
    @GetMapping("/byName/{machineName}")
    public ResponseEntity<Optional<Machinery>> getMachineryByName(@PathVariable String machineName) {
        return new ResponseEntity<Optional<Machinery>>(machineryService.machineryByName(machineName), HttpStatus.OK);
    }

    @PostMapping("/addMachine")


    // Adds a new machinery object, returning a success message and HTTP OK status.
    public ResponseEntity<Map<String, String>> setMachinery(@RequestBody Machinery machine) {
        machineryService.add(machine);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Machine is sucessfully added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Updates an existing machinery by id with provided fields, returns the updated machinery.
    @PatchMapping("/updateMachine/{id}")
    public Machinery updateMachine(@PathVariable String id, @RequestBody Map<String,Object> fields) {
        return machineryService.updateMachine(id,fields);
    }


    // Deletes a machinery by its machineId, returns HTTP No Content status upon successful deletion.

    @DeleteMapping("/deleteMachine/{machineId}")
    public ResponseEntity<String> deleteMachinery(@PathVariable String machineId) {
        machineryService.deleteMachinery(machineId);
        return ResponseEntity.noContent().build();
    }




    // New method to access machine status
    @GetMapping("/getMachineStatus/{machineId}")
    public ResponseEntity<Boolean> getMachineStatus(@PathVariable String machineId) {
        Optional<Machinery> optionalMachine = machineryService.machineryById(machineId);

        if (optionalMachine.isPresent()) {
            return new ResponseEntity<>(optionalMachine.get().getMachineStatus(), HttpStatus.OK);
        } else {
            // Handle the case where the machine wasn't found (optional)
            throw new RuntimeException("Machine not found with id: " + machineId);
            // OR: return ResponseEntity.notFound().build(); (to indicate not found)
        }
    }




    // New method to access machine maintenance list
    @GetMapping("/getMachineMaintenanceHistory/{machineId}")
    public ResponseEntity<List<String>> getMachineMaintenance(@PathVariable String machineId) {
        Optional<Machinery> optionalMachine = machineryService.machineryById(machineId);

        if (optionalMachine.isPresent()) {
            return new ResponseEntity<>(optionalMachine.get().getMaintenance(), HttpStatus.OK);
        } else {
            // Handle the case where the machine wasn't found (optional)
            throw new RuntimeException("Machine not found with id: " + machineId);
            // OR: return ResponseEntity.notFound().build(); (to indicate not found)
        }
    }





}




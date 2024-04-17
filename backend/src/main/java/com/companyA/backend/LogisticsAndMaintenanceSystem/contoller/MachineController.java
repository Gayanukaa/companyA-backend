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
@RequestMapping("/api/v1")
public class MachineController {


    @Autowired
    private MachineryService machineryService;


    @GetMapping("/getMachines")
    public ResponseEntity<List<Machinery>> getAllMachinery() {
        return new ResponseEntity<List<Machinery>>(machineryService.allMachinery(), HttpStatus.OK);
    }

    @GetMapping("/byId/{machineId}")
    public ResponseEntity<Optional<Machinery>> getMachineryById(@PathVariable String machineId) {
        return new ResponseEntity<Optional<Machinery>>(machineryService.machineryById(machineId), HttpStatus.OK);
    }

    @GetMapping("/byName/{machineName}")
    public ResponseEntity<Optional<Machinery>> getMachineryByName(@PathVariable String machineName) {
        return new ResponseEntity<Optional<Machinery>>(machineryService.machineryByName(machineName), HttpStatus.OK);
    }

    @PostMapping("/addMachine")

    public ResponseEntity<Map<String, String>> setMachinery(@RequestBody Machinery machine) {
        machineryService.addMachinery(machine);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Machine is sucessfully added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/updateMachine/{id}")
    public Machinery updateMachine(@PathVariable String id, @RequestBody Map<String,Object> fields) {
        return machineryService.updateMachine(id,fields);
    }

    @DeleteMapping("/deleteMachine/{machineId}")
    public ResponseEntity<String> deleteMachinery(@PathVariable String machineId) {
        machineryService.deleteMachinery(machineId);


        return ResponseEntity.noContent().build();
    }




}

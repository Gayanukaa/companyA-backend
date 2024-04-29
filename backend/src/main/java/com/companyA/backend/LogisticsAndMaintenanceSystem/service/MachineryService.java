package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Machinery;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.MachineryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Service
public class MachineryService extends AbstractService<Machinery> {

    @Autowired
    private MachineryRepository machineryRepository; // Injects the repository used for database operations.

    // Returns all machinery entries from the database.
    public List<Machinery> findAll() {

        return machineryRepository.findAll();
    }

    // Saves a new machinery object to the database.

    public void add(Machinery machine) {
        Optional<Machinery> existingMachine = machineryRepository.findMachineryByMachineId(machine.getMachineId());
        if (existingMachine.isPresent()) {
            throw new RuntimeException("Machinery already exist with id: " + machine.getMachineId());
        } else {
              machineryRepository.save(machine);
        }

    }



    // Finds a machinery by its machineId. Throws an exception if not found.

    public Optional<Machinery> machineryById(String machineId){
        //check is the machine exist
        Optional<Machinery> machine = machineryRepository.findMachineryByMachineId(machineId);
        if (machine.isPresent()) {
            return machine;
        }
        else{
            throw new RuntimeException("Machinery not found with id: " + machineId);
        }

    }


    // Finds a machinery by its name. Throws an exception if not found.
    public Optional<Machinery> machineryByName(String machineName) {
        Optional<Machinery> machine = machineryRepository.findMachineryByMachineName(machineName);
        if (machine.isPresent()) {
            return machine;
        }
        else{ throw new RuntimeException("Machinery not found with name: " + machineName);}



    }


    // Updates fields of an existing machinery using reflection. Saves the updated machinery.

    public Machinery updateMachine(String id, Map<String , Object> fields) {


        Optional<Machinery> existingMachine = machineryRepository.findMachineryByMachineId(id);
        if (existingMachine.isPresent()) {



            fields.forEach((key ,value)->{
                Field field = ReflectionUtils.findField(Machinery.class,key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,existingMachine.get(),value);


            });


           return machineryRepository.save(existingMachine.get());

        }

        else {
            throw new RuntimeException("Machinery not found with id: " + id);
        }




    }

    //Deletes a machinery by its machineId
    public void deleteMachinery(String machineId) {
        // Optional: Check if the machinery exists before trying to delete
        Optional<Machinery> existMachine =machineryRepository.findMachineryByMachineId(machineId);
        if (existMachine.isPresent()) {
            System.out.println("machinery deleted");
          machineryRepository.deleteMachineryByMachineId(machineId);
        }
        else {
            throw new RuntimeException("Machinery not found with id: " + machineId);
        }

        }





}

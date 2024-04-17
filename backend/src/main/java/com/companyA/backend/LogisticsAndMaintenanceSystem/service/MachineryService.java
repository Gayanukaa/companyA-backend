package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Machinery;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.MachineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Service
public class MachineryService {

    @Autowired
    private MachineryRepository machineryRepository;


    public List<Machinery> allMachinery() {

        return machineryRepository.findAll();
    }
    public void addMachinery(Machinery machine) {
        machineryRepository.save(machine);
    }


    public Optional<Machinery> machineryById(String machineId){
        Optional<Machinery> machine = machineryRepository.findMachineryByMachineId(machineId);
        if (machine.isPresent()) {
            return machine;
        }
        else{
            throw new RuntimeException("Machinery not found with id: " + machineId);
        }
        //return machineryRepository.findMachineryByMachineId(machineId);
    }

    public Optional<Machinery> machineryByName(String machineName) {
        Optional<Machinery> machine = machineryRepository.findMachineryByMachineName(machineName);
        if (machine.isPresent()) {
            return machine;
        }
        else{ throw new RuntimeException("Machinery not found with name: " + machineName);}
        //return machineryRepository.findMachineryByMachineName(machineName);


    }


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
        //return null;





    }

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

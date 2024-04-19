package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Machinery;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Technician;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    public List<Technician> allTechnicians() {
        return technicianRepository.findAll();
    }

    public void addTechnician(Technician technician) {
        technicianRepository.save(technician);
    }

    public Optional<Technician> technicianById(String technicianId){
        //return technicianRepository.findTechnicianByTechnicianId(technicianId);

        Optional<Technician> technician = technicianRepository.findTechnicianByTechnicianId(technicianId);
        if (technician.isPresent()) {
            return technician;
        }
        else{
            throw new RuntimeException("Technician not found with Id: " + technicianId);
        }

    }




    public Technician updateTechnician(String id, Map<String , Object> fields) {


        Optional<Technician> existingTechnician = technicianRepository.findTechnicianByTechnicianId(id);
        if (existingTechnician.isPresent()) {



            fields.forEach((key ,value)->{
                Field field = ReflectionUtils.findField(Technician.class,key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingTechnician.get(),value);


            });


            return technicianRepository.save(existingTechnician.get());

        }

        else {
            throw new RuntimeException("Technician not found with id: " + id);
        }
        //return null;






    }

    public void deleteTechnician(String technicianId){
        // Optional: Check if the machinery exists before trying to delete
        Optional<Technician> existTechnician =technicianRepository.findTechnicianByTechnicianId(technicianId);
        if (existTechnician.isPresent()) {
            System.out.println("Technician deleted");
           technicianRepository.deleteTechnicianByTechnicianId(technicianId);
        }
        else {
            throw new RuntimeException("Technician not found with id: " + technicianId);
        }

    }
}






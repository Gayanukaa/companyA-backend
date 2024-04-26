package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.ServiceAndMaintenance;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Technician;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.ServiceAndMaintenanceRepository;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAndMaintenanceService {

    @Autowired
    private ServiceAndMaintenanceRepository serviceAndMaintenanceRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    public List<ServiceAndMaintenance> allServicesAndMaintenance() {
        return serviceAndMaintenanceRepository.findAll();
    }


    public Optional<ServiceAndMaintenance> getServiceAndMaintenanceById(String id) {
        return serviceAndMaintenanceRepository.findById(id);
    }

    public ServiceAndMaintenance saveServiceAndMaintenance(ServiceAndMaintenance serviceAndMaintenance) {
        return serviceAndMaintenanceRepository.save(serviceAndMaintenance);
    }

    public void deleteServiceAndMaintenance(String id) {
        serviceAndMaintenanceRepository.deleteById(id);
    }

    // Method to assign technician to maintenance
    public void assignTechnician(String serviceId, String technicianId) {
        Optional<ServiceAndMaintenance> optionalServiceAndMaintenance = serviceAndMaintenanceRepository.findById(serviceId);
        Optional<Technician> optionalTechnician = technicianRepository.findById(technicianId);

        if (optionalServiceAndMaintenance.isPresent() && optionalTechnician.isPresent()) {
            ServiceAndMaintenance serviceAndMaintenance = optionalServiceAndMaintenance.get();
            Technician technician = optionalTechnician.get();
            serviceAndMaintenance.assignTechnician(String.valueOf(technician));
            serviceAndMaintenanceRepository.save(serviceAndMaintenance);
        }
    }


    // Method to generate service report
    public void generateServiceReport(String serviceId) {
        Optional<ServiceAndMaintenance> optionalServiceAndMaintenance = serviceAndMaintenanceRepository.findById(serviceId);
        if (optionalServiceAndMaintenance.isPresent()) {
            ServiceAndMaintenance serviceAndMaintenance = optionalServiceAndMaintenance.get();
            serviceAndMaintenance.generateServiceReport();
            serviceAndMaintenanceRepository.save(serviceAndMaintenance);
        }
    }

    public ServiceAndMaintenance scheduleMaintenance(ServiceAndMaintenance maintenance) {
        // Implement scheduling logic here, for example, you can set some default values
        // or validate the maintenance before saving
        return serviceAndMaintenanceRepository.save(maintenance);
    }


}

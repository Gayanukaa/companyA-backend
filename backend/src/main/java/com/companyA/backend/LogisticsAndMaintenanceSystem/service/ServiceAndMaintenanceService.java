package com.companyA.backend.LogisticsAndMaintenanceSystem.service;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.ServiceAndMaintenance;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Technician;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vehicle;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.ServiceAndMaintenanceRepository;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    public ServiceAndMaintenance getServiceAndMaintenanceById(String ServiceId) {
        return serviceAndMaintenanceRepository.findByServiceId(ServiceId);
    }

    public ServiceAndMaintenance saveServiceAndMaintenance(ServiceAndMaintenance serviceAndMaintenance) {
        return serviceAndMaintenanceRepository.save(serviceAndMaintenance);
    }

    public void deleteServiceAndMaintenance(String ServiceId) {
        serviceAndMaintenanceRepository.deleteByServiceId(ServiceId);
    }

    // Method to assign technician to maintenance
    public String assignTechnician(String serviceId, String technicianId) {
        ServiceAndMaintenance serviceAndMaintenance = serviceAndMaintenanceRepository.findByServiceId(serviceId);
        Technician technician = technicianRepository.findTechnicianByTechnicianId(technicianId).orElse(null);

        if (serviceAndMaintenance == null || technician == null) {
            return HttpStatus.NOT_FOUND.toString();
        }
        else{
            serviceAndMaintenance.setTechnicianId(technician.getTechnicianId());
            serviceAndMaintenanceRepository.save(serviceAndMaintenance);
        }
        return HttpStatus.OK.toString();
    }

}

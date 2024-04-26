package com.companyA.backend.LogisticsAndMaintenanceSystem.contoller;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.ServiceAndMaintenance;
import com.companyA.backend.LogisticsAndMaintenanceSystem.service.ServiceAndMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/SM1/service-maintenance")
public class ServiceAndMaintenanceController {

    @Autowired
    private ServiceAndMaintenanceService serviceAndMaintenanceService;

    @GetMapping
    public ResponseEntity<List<ServiceAndMaintenance>> getAllServicesAndMaintenance() {
        return new ResponseEntity<>(serviceAndMaintenanceService.allServicesAndMaintenance(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ServiceAndMaintenance> getServiceAndMaintenanceById(@PathVariable String id) {
        Optional<ServiceAndMaintenance> serviceAndMaintenance = serviceAndMaintenanceService.getServiceAndMaintenanceById(id);
        return serviceAndMaintenance.map(service -> new ResponseEntity<>(service, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<ServiceAndMaintenance> saveServiceAndMaintenance(@RequestBody ServiceAndMaintenance serviceAndMaintenance) {
        ServiceAndMaintenance savedServiceAndMaintenance = serviceAndMaintenanceService.saveServiceAndMaintenance(serviceAndMaintenance);
        return new ResponseEntity<>(savedServiceAndMaintenance, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceAndMaintenance(@PathVariable String id) {
        serviceAndMaintenanceService.deleteServiceAndMaintenance(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to assign technician to maintenance
    @PostMapping("/{serviceId}/assignTechnician/{technicianId}")
    public ResponseEntity<Void> assignTechnicianToMaintenance(@PathVariable String serviceId, @PathVariable String technicianId) {
        serviceAndMaintenanceService.assignTechnician(serviceId, technicianId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{serviceId}/generateServiceReport")
    public ResponseEntity<String> generateServiceReport(@PathVariable String serviceId) {
        serviceAndMaintenanceService.generateServiceReport(serviceId);
        return ResponseEntity.ok().body("Service report generated successfully for service ID: " + serviceId);
    }


    @PostMapping("/schedule")
    public ResponseEntity<ServiceAndMaintenance> scheduleMaintenance(@RequestBody ServiceAndMaintenance serviceAndMaintenance) {
        ServiceAndMaintenance scheduledMaintenance = serviceAndMaintenanceService.scheduleMaintenance(serviceAndMaintenance);
        return new ResponseEntity<>(scheduledMaintenance, HttpStatus.CREATED);
    }
}

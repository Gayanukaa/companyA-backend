package com.companyA.backend.LogisticsAndMaintenanceSystem.contoller;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.ServiceAndMaintenance;
import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vehicle;
import com.companyA.backend.LogisticsAndMaintenanceSystem.service.ServiceAndMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/SM1/service-maintenance")
@CrossOrigin
public class ServiceAndMaintenanceController {

    @Autowired
    private ServiceAndMaintenanceService serviceAndMaintenanceService;

    @GetMapping
    public ResponseEntity<List<ServiceAndMaintenance>> getAllServicesAndMaintenance() {
        return new ResponseEntity<>(serviceAndMaintenanceService.allServicesAndMaintenance(), HttpStatus.OK);
    }


    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceAndMaintenance> getServiceAndMaintenanceById(@PathVariable String serviceId) {
        Optional<ServiceAndMaintenance> optionalServiceAndMaintenance = Optional.ofNullable(serviceAndMaintenanceService.getServiceAndMaintenanceById(serviceId));

        return optionalServiceAndMaintenance.map(service -> new ResponseEntity<>(service, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @PostMapping
    public ResponseEntity<ServiceAndMaintenance> saveServiceAndMaintenance(@RequestBody ServiceAndMaintenance serviceAndMaintenance) {
        ServiceAndMaintenance savedServiceAndMaintenance = serviceAndMaintenanceService.saveServiceAndMaintenance(serviceAndMaintenance);
        return new ResponseEntity<>(savedServiceAndMaintenance, HttpStatus.CREATED);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteServiceAndMaintenance(@PathVariable String serviceId) {
        serviceAndMaintenanceService.deleteServiceAndMaintenance(serviceId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to assign technician to maintenance
    @PutMapping("/assignTechnician/{serviceId}/{technicianId}")
    public ResponseEntity<String> assignTechnicianToMaintenance(@PathVariable String serviceId, @PathVariable String technicianId) {
        serviceAndMaintenanceService.assignTechnician(serviceId, technicianId);
        return ResponseEntity.ok().build();
    }


}

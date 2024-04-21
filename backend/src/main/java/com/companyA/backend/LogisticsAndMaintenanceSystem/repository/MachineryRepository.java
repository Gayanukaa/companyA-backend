package com.companyA.backend.LogisticsAndMaintenanceSystem.repository;

import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Machinery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineryRepository extends MongoRepository<Machinery,String> {
    Optional<Machinery> findMachineryByMachineId(String machineId);
    Optional<Machinery> findMachineryByMachineName(String machineName);

    void deleteMachineryByMachineId(String machineId);

}

package com.companyA.backend.LogisticsAndMaintenanceSystem.repository;


import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String> {
    Optional<Vendor> findVendorByVendorId(String vendorId);
    void deleteVendorByVendorId(String vendorId);
}

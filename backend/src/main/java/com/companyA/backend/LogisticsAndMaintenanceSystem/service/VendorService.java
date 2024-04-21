package com.companyA.backend.LogisticsAndMaintenanceSystem.service;


import com.companyA.backend.LogisticsAndMaintenanceSystem.model.Vendor;
import com.companyA.backend.LogisticsAndMaintenanceSystem.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> findAllVendors() {
        return vendorRepository.findAll();
    }

    public void addVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public Optional<Vendor> findVendorById(String vendorId) {
        Optional<Vendor> vendor = vendorRepository.findVendorByVendorId(vendorId);
        if (vendor.isPresent()) {
            return vendor;
        } else {
            throw new RuntimeException("Vendor not found with id: " + vendorId);
        }

    }

    public Vendor updateVendor(String id, Map<String, Object> fields) {


        Optional<Vendor> existingVendor = vendorRepository.findVendorByVendorId(id);
        if (existingVendor.isPresent()) {


            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Vendor.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingVendor.get(), value);


            });


            return vendorRepository.save(existingVendor.get());

        } else {
            throw new RuntimeException("Vendor not found with id: " + id);
        }
    }


    public void deleteVendor(String vendorId) {

        Optional<Vendor> existVendor = vendorRepository.findVendorByVendorId(vendorId);
        if (existVendor.isPresent()) {
            System.out.println("Vendor deleted");
            vendorRepository.deleteVendorByVendorId(vendorId);
        } else {
            throw new RuntimeException("Vendor not found with id: " + vendorId);
        }


    }
}





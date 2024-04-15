package com.companyA.backend.InventoryManagementSystem.repository;

import com.companyA.backend.InventoryManagementSystem.model.Stocks;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StocksRepository extends InventoryRepository<Stocks>{
    List<Stocks> findByPrice(float price);


    @Query(value = "SELECT * FROM Stocks s WHERE s.quantity < s.thresholdQuantity")
    List<Stocks> findAllByQuantityLessThanThresholdQuantity();

    /*@Query("{ 'quantity' : { $lt : 'thresholdQuantity' } }")
    List<Stocks> findAllByQuantityLessThanThresholdQuantity();*/
}
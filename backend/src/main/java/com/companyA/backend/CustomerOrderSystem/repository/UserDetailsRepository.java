package com.companyA.backend.CustomerOrderSystem.repository;
import com.companyA.backend.CustomerOrderSystem.model.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, ObjectId> {
}

package com.companyA.backend.TrainingAndDevelopmentSystem.repository;

import com.companyA.backend.TrainingAndDevelopmentSystem.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CourseRepository extends MongoRepository<Course,String> {
    @Query(value = "{ 'courseId' : ?0 }", fields = "{ 'link' : 1 ,'id' : 0}")
    String findCourseLinkById(Long courseId);

    @Query(value = "{ 'courseId' : ?0 }", fields = "{ 'Details' : 1 ,'id' : 0}")
    String findCourseDetailsById(Long courseId);
}

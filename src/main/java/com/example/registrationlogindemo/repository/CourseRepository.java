package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);
}

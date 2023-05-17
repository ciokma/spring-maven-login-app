package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByName(String name);
}

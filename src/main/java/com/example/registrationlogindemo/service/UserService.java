package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.CourseDto;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Course;
import com.example.registrationlogindemo.entity.Instructor;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    Instructor findByEmail(String email);

    List<UserDto> findAllUsers();
    
    void saveCourse(CourseDto courseDto);
    
    Course findCourseByName(String name);
    List<CourseDto> findAllCourses();
    
}

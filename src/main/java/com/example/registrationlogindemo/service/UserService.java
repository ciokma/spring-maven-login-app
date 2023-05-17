package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.CourseDto;
import com.example.registrationlogindemo.dto.StudentDto;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Course;
import com.example.registrationlogindemo.entity.Instructor;
import com.example.registrationlogindemo.entity.Student;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    Instructor findByEmail(String email);

    List<UserDto> findAllUsers();
    //Course
    void saveCourse(CourseDto courseDto);
    
    Course findCourseByName(String name);
    List<CourseDto> findAllCourses();
    
    //Student
    void saveStudent(StudentDto studentDto);
    
    Student findStudentByName(String name);
    List<StudentDto> findAllStudents();
    
	public String getPsw(String psw);

    
}

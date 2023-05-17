package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.dto.CourseDto;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.Course;
import com.example.registrationlogindemo.entity.Instructor;
import com.example.registrationlogindemo.repository.RoleRepository;
import com.example.registrationlogindemo.repository.CourseRepository;
import com.example.registrationlogindemo.repository.InstructorRepository;
import com.example.registrationlogindemo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private InstructorRepository instructorRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CourseRepository courseRepository;
    public UserServiceImpl(InstructorRepository instructorRepository,
                           RoleRepository roleRepository,
                           CourseRepository courseRepository,
                           PasswordEncoder passwordEncoder
                          ) {
        this.instructorRepository = instructorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        Instructor instructor = new Instructor();
        instructor.setName(userDto.getFirstName() + " " + userDto.getLastName());
        instructor.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        instructor.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        instructor.setRoles(Arrays.asList(role));
        instructorRepository.save(instructor);
    }

    @Override
    public Instructor findByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(Instructor instructor){
        UserDto userDto = new UserDto();
        String[] name = instructor.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(instructor.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

	@Override
	public void saveCourse(CourseDto courseDto) {
		    Course course = new Course();
		    course.setName(courseDto.getName());
	        course.setDayOfWeek(courseDto.getDayOfWeek());
	        course.setDropInPrice(courseDto.getDropInPrice());
	        course.setTimeCourse(courseDto.getTimeCourse());
	        course.setDescription(courseDto.getDescription());


	        courseRepository.save(course);
		
	}


	@Override
	public Course findCourseByName(String name) {
		// TODO Auto-generated method stub
        return courseRepository.findByName(name);
	}

	@Override
	public List<CourseDto> findAllCourses() {
		List<Course> courses = courseRepository.findAll();
        return courses.stream().map((course) -> convertEntityCourseToDto(course))
                .collect(Collectors.toList());
    }

    private CourseDto convertEntityCourseToDto(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setName(course.getName());
        courseDto.setDayOfWeek(course.getDayOfWeek());
        courseDto.setDropInPrice(course.getDropInPrice());
        courseDto.setTimeCourse(course.getTimeCourse());
        courseDto.setDescription(course.getDescription());
        return courseDto;
    }
	
}

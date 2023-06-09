package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.CourseDto;
import com.example.registrationlogindemo.dto.StudentDto;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Course;
import com.example.registrationlogindemo.entity.Instructor;
import com.example.registrationlogindemo.entity.Student;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        Instructor existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        //validating password and confirm password
        String psw1 = userService.getPsw(user.getPassword());
        String psw2 = userService.getPsw(user.getConfirmPassword());

        if (!psw1.equals(psw2)) {
        	result.rejectValue("password", null, "the password and confirm pw fields are not equal, please try again.");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    // course method
    
    
    // handler method to handle user registration request
    @GetMapping("course")
    public String course(Model model){
    	CourseDto course = new CourseDto();
        model.addAttribute("course", course);
        return "course";
    }
    
    
 

    // handler method to handle register user form submit request
    @PostMapping("/register_course")
    public String register_course(@Valid @ModelAttribute("course") CourseDto course,
                               BindingResult result,
                               Model model){
        Course existing = userService.findCourseByName(course.getName());
        if (existing != null && course.getId() == 0) {
            result.rejectValue("email", null, "There is already an existing course registered with that name");
        }
        if (result.hasErrors()) {
            model.addAttribute("course", course);
            return "register";
        }
        userService.saveCourse(course);
        return "redirect:/course?success";
    }

    @GetMapping("/courses")
    public String listRegisteredCourses(Model model){
        List<CourseDto> courses = userService.findAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }
    
	@GetMapping("/edit/{name}")
	public String edit(@PathVariable String name, Model model){
		Course course=userService.findCourseByName(name);
		model.addAttribute("course", course);
		return "course";
	}
	
	
	
// student method
    
    
    // handler method to handle user registration request
    @GetMapping("student")
    public String student(Model model){
    	StudentDto student = new StudentDto();
        model.addAttribute("student", student);
        return "student";
    }
    
    
 

    // handler method to handle register user form submit request
    @PostMapping("/register_student")
    public String register_student(@Valid @ModelAttribute("course") StudentDto student,
                               BindingResult result,
                               Model model){
        Student existing = userService.findStudentByName(student.getName());
        if (existing != null && student.getId() == 0) {
            result.rejectValue("email", null, "There is already an existing student registered with that name");
        }
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "student";
        }
        userService.saveStudent(student);
        return "redirect:/student?success";
    }

    @GetMapping("/students")
    public String listRegisteredStudents(Model model){
        List<StudentDto> students = userService.findAllStudents();
        model.addAttribute("students", students);
        return "students";
    }
    @GetMapping("/editStudent/{name}")
	public String editStudent(@PathVariable String name, Model model){
		Student student=userService.findStudentByName(name);
		model.addAttribute("student", student);
		return "student";
	}
	

	
	
}

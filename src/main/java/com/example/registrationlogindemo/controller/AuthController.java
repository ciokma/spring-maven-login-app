package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.CourseDto;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Course;
import com.example.registrationlogindemo.entity.Instructor;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        if (existing != null) {
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
}

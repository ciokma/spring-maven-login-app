package com.example.registrationlogindemo.security;

import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.Instructor;
import com.example.registrationlogindemo.repository.InstructorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private InstructorRepository instructorRepository;

    public CustomUserDetailsService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Instructor instructor = instructorRepository.findByEmail(email);

        if (instructor != null) {
            return new org.springframework.security.core.userdetails.User(instructor.getEmail(),
                    instructor.getPassword(),
                    mapRolesToAuthorities(instructor.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}


package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="courses")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @Column(nullable=false)
    private String dayOfWeek;
    
    @Column(nullable=false)
    private int dropInPrice;
    
    @Column(nullable=false)
    private String timeCourse;
    
    @Column(nullable=false)
    private String description;

	@ManyToMany(mappedBy="courses")
    private List<Instructor> instructors;
	
	@ManyToMany(mappedBy="courses")
    private List<Student> students;



	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getDropInPrice() {
		return dropInPrice;
	}

	public void setDropInPrice(int dropInPrice) {
		this.dropInPrice = dropInPrice;
	}

	public String getTimeCourse() {
		return timeCourse;
	}

	public void setTimeCourse(String timeCourse) {
		this.timeCourse = timeCourse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}
}

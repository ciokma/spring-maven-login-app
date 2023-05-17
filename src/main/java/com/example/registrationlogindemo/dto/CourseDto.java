package com.example.registrationlogindemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    	
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
	public int getDropInPrice() {
		return dropInPrice;
	}
	public void setDropInPrice(int dropInPrice) {
		this.dropInPrice = dropInPrice;
	}
	private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String dayOfWeek;
    @NotNull
    private int dropInPrice;
   
	@NotEmpty
    private String timeCourse;
    @NotEmpty
    private String description;

}
